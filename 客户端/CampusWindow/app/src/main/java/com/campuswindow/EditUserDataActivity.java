package com.campuswindow;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.campuswindow.constant.UserConstant;
import com.campuswindow.entity.ModifyInformationDto;
import com.campuswindow.richeditor.Utils;
import com.campuswindow.server.API;
import com.campuswindow.service.index.ModifyUserDataService;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EditUserDataActivity extends AppCompatActivity {
    private Button backBtn;
    private ImageView imgHeader;
    private TextView name,sex,num;
    private EditText label;
    private Button save;
    private ActivityResultLauncher<Intent> launcher;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_data);
        findViews();
        createLauncher();
        name.setText(getIntent().getStringExtra("setName"));
        Glide.with(this)
                .load(getIntent().getStringExtra("avatar"))
                .circleCrop()
                .into(imgHeader);
        setListenerMethods();
    }



    private void setListenerMethods() {
        //更换头像：
        imgHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.
                        PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EditUserDataActivity.this, new
                            String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
            }
        });
        //回退
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //设置名字
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditUserDataActivity.this,MineSetNameActivity.class);
                intent.putExtra("name",name.getText().toString());
                intent.putExtra("avatar",imageUri);
                startActivity(intent);
                finish();
            }
        });
        //设置性别
        sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(EditUserDataActivity.this,R.style.BottomSheetDialog);
                View bottomView = LayoutInflater.from(EditUserDataActivity.this).inflate(R.layout.bottom_sheet_layout,null);
                Button no = bottomView.findViewById(R.id.bottom_sheet_no);
                RadioGroup radioGroup = bottomView.findViewById(R.id.bottom_sheet_rp);
                RadioButton rb1 = bottomView.findViewById(R.id.bottom_sheet_rb1);
                RadioButton rb2 = bottomView.findViewById(R.id.bottom_sheet_rb2);
                RadioButton rb3 = bottomView.findViewById(R.id.bottom_sheet_rb3);
                bottomSheetDialog.setContentView(bottomView);
                bottomSheetDialog.show();
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId){

                            case R.id.bottom_sheet_rb1:
                                Toast.makeText(EditUserDataActivity.this, "你选择了"+rb1.getText().toString(), Toast.LENGTH_SHORT).show();
                                sex.setText(rb1.getText().toString());
                                bottomSheetDialog.dismiss();
                                break;
                            case R.id.bottom_sheet_rb2:
                                Toast.makeText(EditUserDataActivity.this, "你选择了"+rb2.getText().toString(), Toast.LENGTH_SHORT).show();
                                sex.setText(rb2.getText().toString());
                                bottomSheetDialog.dismiss();
                                break;
                            case R.id.bottom_sheet_rb3:
                                Toast.makeText(EditUserDataActivity.this, "你选择了"+rb3.getText().toString(), Toast.LENGTH_SHORT).show();
                                sex.setText(rb3.getText().toString());
                                bottomSheetDialog.dismiss();
                                break;
                        }
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        });
        //设置个性签名
        label.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int charNum = s.length();
                num.setText(charNum + "/100");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //提交用户信息，保存
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String mName = name.getText().toString();
                        int gender;
                        if("男".equals(sex.getText().toString())){
                            gender = 0;
                        } else if ("女".equals(sex.getText().toString())) {
                            gender = 1;
                        }else{
                            gender = 3;
                        }
                        String mLabel = label.getText().toString();
                        ModifyInformationDto modifyInformationDto = new ModifyInformationDto(UserConstant.USER_ID,mName,gender,mLabel);
                        ModifyUserDataService service = new ModifyUserDataService();
                        Result result = service.getUserData(modifyInformationDto);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if("成功".equals(result.getMsg())){
                                    Toast.makeText(EditUserDataActivity.this, "信息修改成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Toast.makeText(EditUserDataActivity.this, "信息修改失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }).start();
            }
        });
    }

    private void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        launcher.launch(intent);
    }
    private void createLauncher() {
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    imageUri = data.getData();
                    Glide.with(EditUserDataActivity.this)
                            .load(imageUri)
                            .circleCrop()
                            .into(imgHeader);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String result = uploadAvatar();
                            Log.i("result",result);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if("成功".equals(result)){
                                        Toast.makeText(getApplicationContext(), "上传成功", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }).start();
                }
            }
        });
    }

    private String uploadAvatar() {
        String realPath = Utils.getRealPath(getApplicationContext(), imageUri);
        Log.i("realPath",realPath);
        File file = new File(realPath);
        OkHttpClient okHttpClient = new OkHttpClient();
        MultipartBody requestBody = new MultipartBody.Builder()
                .addFormDataPart("userId", UserConstant.USER_ID)
                .addFormDataPart("avatar",file.getName(), RequestBody.create(file, MediaType.get("image/*")))
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder
                .post(requestBody)
                .url(API.USER_AVATAR)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String string = response.body().string();
            Gson gson = new Gson();
            Result result = gson.fromJson(string, Result.class);
            return result.getMsg();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void findViews() {
        backBtn = findViewById(R.id.edit_user_data_back_btn);
        name = findViewById(R.id.edit_user_name);
        sex = findViewById(R.id.edit_user_sex);
        num = findViewById(R.id.edit_user_label_num);
        label = findViewById(R.id.edit_user_label);
        imgHeader = findViewById(R.id.edit_user_img);
        save = findViewById(R.id.edit_user_data_save_btn);
    }
}