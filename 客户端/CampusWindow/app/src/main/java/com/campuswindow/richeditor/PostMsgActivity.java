package com.campuswindow.richeditor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.afollestad.materialdialogs.MaterialDialog;
import com.campuswindow.NewActivity;
import com.campuswindow.R;
import com.campuswindow.Result;
import com.campuswindow.constant.UserConstant;
import com.campuswindow.server.API;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.wasabeef.richeditor.RichEditor;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostMsgActivity extends AppCompatActivity {
    private RichEditor mEditor;
    private ActivityResultLauncher<Intent> launcher;
    private Uri imageUri;
    private OkHttpClient okHttpClient = new OkHttpClient();
    private EditText postMsgTitle;
    private Spinner spin;
    private String where;
    private Button backBtn;

    private List<String> images = new ArrayList<>();
    private List<String> videos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_msg);

        findViews();
        setListenersMethods();

        createLauncher();
        mEditor = (RichEditor) findViewById(R.id.editor);
        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(22);

        mEditor.setPadding(10, 10, 10, 10);
        mEditor.setPlaceholder("Insert text here...");


        findViewById(R.id.action_undo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.undo();
            }
        });

        findViewById(R.id.action_redo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.redo();
            }
        });

        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setBold();
            }
        });

        findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
            private boolean isSelected = false;
            @Override
            public void onClick(View v) {
                mEditor.setItalic();
            }
        });

        findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setUnderline();
            }
        });

        findViewById(R.id.action_heading1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(1);
            }
        });

        findViewById(R.id.action_heading2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(2);
            }
        });

        findViewById(R.id.action_heading3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(3);
            }
        });

//        findViewById(R.id.action_heading4).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setHeading(4);
//            }
//        });
//
//        findViewById(R.id.action_heading5).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setHeading(5);
//            }
//        });

        findViewById(R.id.action_txt_color).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mEditor.focusEditor();
                new MaterialDialog.Builder(PostMsgActivity.this)
                        .title("选择字体颜色")
                        .items(R.array.color_items)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                dialog.dismiss();
                                switch (which) {
                                    case 0://红
                                        mEditor.setTextColor(Color.RED);
                                        break;
                                    case 1://黄
                                        mEditor.setTextColor(Color.YELLOW);
                                        break;
                                    case 2://蓝
                                        mEditor.setTextColor(Color.BLUE);
                                        break;
                                    case 3://绿
                                        mEditor.setTextColor(Color.GREEN);
                                        break;
                                    case 4://黑
                                        mEditor.setTextColor(Color.BLACK);
                                        break;
                                }
                                return false;
                            }
                        }).show();
            }
        });

        findViewById(R.id.action_bg_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.focusEditor();
                new MaterialDialog.Builder(PostMsgActivity.this)
                        .title("选择字体背景颜色")
                        .items(R.array.text_back_color_items)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                dialog.dismiss();
                                switch (which) {
                                    case 0://红
                                        mEditor.setTextBackgroundColor(Color.RED);
                                        break;
                                    case 1://黄
                                        mEditor.setTextBackgroundColor(Color.YELLOW);
                                        break;
                                    case 2://绿
                                        mEditor.setTextBackgroundColor(Color.GREEN);
                                        break;
                                    case 3://蓝
                                        mEditor.setTextBackgroundColor(Color.BLUE);
                                        break;
                                    case 4://黑
                                        mEditor.setTextBackgroundColor(Color.BLACK);
                                        break;
                                    case 5://透明
                                        mEditor.setTextBackgroundColor(Color.WHITE);
                                        break;
                                }
                                return false;
                            }
                        }).show();
            }
        });

        findViewById(R.id.action_indent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setIndent();
            }
        });

        findViewById(R.id.action_outdent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setOutdent();
            }
        });

        findViewById(R.id.action_align_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setAlignLeft();
            }
        });

        findViewById(R.id.action_align_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setAlignCenter();
            }
        });

        findViewById(R.id.action_align_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setAlignRight();
            }
        });

        findViewById(R.id.action_insert_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.
                        PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PostMsgActivity.this, new
                            String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum("image/*");
                }
            }
        });
        findViewById(R.id.action_insert_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.
                        PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PostMsgActivity.this, new
                            String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum("video/*");
                }
            }
        });

        //发帖
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(postMsgTitle.getText().toString().equals("")){
                    Toast.makeText(PostMsgActivity.this, "请输入标题！！！", Toast.LENGTH_SHORT).show();
                } else if ("请选择……".equals(where)) {
                    Toast.makeText(PostMsgActivity.this, "请您选择要发送帖子的位置！！！", Toast.LENGTH_SHORT).show();
                }else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            sendActivity();
                        }
                    }).start();
                }
            }
        });
    }

    private void setListenersMethods() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostMsgActivity.this, NewActivity.class);
                startActivity(intent);
                finish();
            }
        });
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                where = parent.getItemAtPosition(position).toString();
                Toast.makeText(PostMsgActivity.this, "您选择了"+where, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void findViews() {
        postMsgTitle = findViewById(R.id.post_msg_edt);
        spin = findViewById(R.id.post_msg_spin);
        backBtn = findViewById(R.id.post_msg_back_btn);
    }

    public static List<String> getMatchString(String regex, String string) {
        List<String> pics = new ArrayList<>();
        Pattern compile = compile = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = compile.matcher(string);
        while (matcher.find()) {
            String img = matcher.group();
//            pics.add(img); // 如果只需要标签，那到这一步就可以了，如果直接需要图片URL，copy代码到最后
            /**
             * reg_html_img_src_http: 获取src中 "" 图片地址的正则("\"http?(.*?)(\"|>|\\s+)")
             *
             * 获取标签中src的正则表达式（"src\\s*=\\s*\"?(.*?)(\"|>|\\s+)"）
             */
            Matcher m = Pattern.compile("\"http?(.*?)(\"|>|\\s+)").matcher(img);
            m.find();
            String group = m.group();
            pics.add(group.substring(1, group.length() - 1));
        }
        return pics;
    }


    private void sendActivity() {
        String activityContent = mEditor.getHtml();
        System.out.println(activityContent);
        List<String> images = getMatchString("<img.*?>", activityContent);
        List<String> videos = getMatchString("<video.*?>", activityContent);
        EntertainmentActivityDto entertainmentActivityDto;
        if("学术活动".equals(where)){
            entertainmentActivityDto = new EntertainmentActivityDto(postMsgTitle.getText().toString(),
                    activityContent, UserConstant.USER_ID, images, videos,0);
        }else if("娱乐".equals(where)){
            entertainmentActivityDto = new EntertainmentActivityDto(postMsgTitle.getText().toString(),
                    activityContent, UserConstant.USER_ID, images, videos,1);
        }else{
            entertainmentActivityDto = new EntertainmentActivityDto(postMsgTitle.getText().toString(),
                    activityContent, UserConstant.USER_ID, images, videos,2);
        }

        Gson gson = new Gson();
        String json = gson.toJson(entertainmentActivityDto);
        Log.i("JSON",json);
        RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API.SERVER_URL + API.SEND_ACTIVITY)
                .post(requestBody)
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            Result result = gson.fromJson(response.body().string(), Result.class);

            if (result.getMsg().equals("成功")){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "发帖成功", Toast.LENGTH_SHORT);
                        Intent intent= new Intent(PostMsgActivity.this,NewActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void openAlbum (String type) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(type);
        launcher.launch(intent);
    }

    private void createLauncher () {
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    Log.i("Result",result+"");
                    Intent intent = result.getData();
                    Log.i("intent",intent+"" + "  "  +intent.toString());
                    imageUri = intent.getData();
                    Log.i("imageUri",""+imageUri);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String realPath = Utils.getRealPath(getApplicationContext(), imageUri);
                            Log.i("realPath",realPath+"");
                            String filePath = uploadAvatar(realPath, API.FILE_UPLOAD, Utils.TYPE);
                            Log.i("filePath",filePath);
                            if (Utils.TYPE.equals("video/mp4")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        String currentHtml = mEditor.getHtml() == null ? "" : mEditor.getHtml();
                                        mEditor.setHtml(currentHtml + "<video src=\"" + filePath  + "#t=0.01\" preload=“metadata” " + "\" style=\"max-width:100%\" controls=\"\"></video><br><br>");
                                    }
                                });
                            }else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        String currentHtml = mEditor.getHtml() == null ? "" : mEditor.getHtml();
                                        System.out.println(currentHtml);
                                        mEditor.setHtml(currentHtml + "<img src=\"" + filePath + "\" style=\"max-width:100%\"></img><br><br>");
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }
        });
    }

    private String uploadAvatar(String realPath, String api, String mediaType) {
        File file = new File(realPath);
        MultipartBody requestBody = new MultipartBody.Builder()
                .addFormDataPart("file", file.getName(), RequestBody.create(file, MediaType.get(mediaType)))
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder
                .post(requestBody)
                .url(API.SERVER_URL + api)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String string = response.body().string();
            Gson gson = new Gson();
            Result result = gson.fromJson(string, Result.class);
            return (String) result.getData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}