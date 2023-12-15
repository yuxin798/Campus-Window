package com.campuswindow.fragment.index;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.campuswindow.EditUserDataActivity;
import com.campuswindow.R;
import com.campuswindow.Result;
import com.campuswindow.adapter.MineAdapter;
import com.campuswindow.chat.ChatUserDto;
import com.campuswindow.constant.UserConstant;
import com.campuswindow.entity.User;
import com.campuswindow.fragment.mine.CollectFragment;
import com.campuswindow.fragment.mine.CommentFragment;
import com.campuswindow.fragment.mine.IssueFragment;
import com.campuswindow.richeditor.Utils;
import com.campuswindow.server.API;
import com.campuswindow.service.index.SearchOneSelfService;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MineFragment extends Fragment {
    private TabLayout mineFgTbl;
    private ViewPager2 mineFgVp2;
    private List<Fragment> fragments;
    private MineAdapter mineAdapter;
    private ImageView btnNavig;
    private DrawerLayout mineDra;
    private NavigationView mineNav;
    private ActivityResultLauncher<Intent> launcher;
    private Uri imageUri;
    private Button editData;
    private TextView name,label;
    private ImageView imgHead;
    private User user;
    private Handler handler;

    public MineFragment() {
        handler = new Handler(Looper.getMainLooper());
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.activity_mine_fragment,null);
        initPages();
        getViews(page);

        getUserMsg();

        createLauncher();

        setBtnlistener();//设置按钮事件
        dealNavigation();//处理侧滑栏点击事件

        mineAdapter = new MineAdapter(fragments,getActivity());
        mineFgVp2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mineFgVp2.setAdapter(mineAdapter);
        defineMediator();
        return page;
    }

    private void getUserMsg() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SearchOneSelfService service = new SearchOneSelfService();
                Result result = service.getUserData();
                System.out.println(result+"66666");
                Gson gson = new Gson();
                user = gson.fromJson(gson.toJson(result.getData()),User.class);
                runOnMainThread();
            }
        }).start();
    }

    private void runOnMainThread() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                //初始化头像
                Glide.with(requireActivity())
                        .load(user.getAvatar())
                        .circleCrop()
                        .into(imgHead);
                //初始化名称
                name.setText(user.getUserName());
                //初始化
                label.setText(user.getSignature());
            }
        });
    }


    /**
 * 侧滑栏项目item处理事件
 * */
    private void dealNavigation() {
        mineNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch(item.getItemId()){
//                    case R.id.nav_camera:
//                        break;
//                    case R.id.nav_deal:
//                        break;
//                    case R.id.nav_gallery:
//                        break;
//                    case R.id.nav_manage:
//                        break;
//                    case R.id.nav_send:
//                        break;
//                    case R.id.nav_share:
//                        break;
//                    case R.id.nav_slideshow:
//                        break;
//                }
                Toast.makeText(getActivity(),item.getTitle().toString(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    /**
 * 设置点击事件
 * */
    private void setBtnlistener() {
        btnNavig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mineDra.openDrawer(GravityCompat.END);
            }
        });

        //更换头像
        imgHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.
                        PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new
                            String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                    }else{
                        openAlbum();
                    }
                }
            //TODO 上传更换头像：
        });
        //编辑资料
        editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();
                        Request build = new Request.Builder()
                                .get()
                                .url(API.FIND_CHAT_USER_DTO + "?userId=" + UserConstant.USER_ID)
                                .build();
                        Call call = client.newCall(build);
                        try {
                            Response response = call.execute();
                            Gson gson = new Gson();
                            Result result = gson.fromJson(response.body().string(), Result.class);
                            Type type = new TypeToken<ChatUserDto>(){}.getType();
                            ChatUserDto chatUserDto = gson.fromJson(gson.toJson(result.getData()), type);
                            Log.i("chatUserDto",chatUserDto.toString());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    String userName = name.getText().toString();
                                    Intent intent = new Intent(getActivity(), EditUserDataActivity.class);
                                    intent.putExtra("setName",userName);
                                    intent.putExtra("avatar",chatUserDto.getAvatar());
                                    Log.i("imageUri",chatUserDto.getAvatar());
                                    startActivity(intent);
                                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                }
                            });
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();

            }
        });
//        //向上滚动时，新的标题样式：
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
//                if(Math.abs(i)==ivtag.getBottom()){
//                    pos = Math.abs(i);
//                    ivtag.setVisibility(View.GONE);
//                    mineFgTbl.setVisibility(View.INVISIBLE);
//                }else{
//                    pos=0;
//                    mineFgTbl.setVisibility(View.GONE);
//                    ivtag.setVisibility(View.INVISIBLE);
//                }
//            }
//        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else{
                    Toast.makeText(getActivity(),"你拒绝了",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        launcher.launch(intent);
    }

    private void createLauncher() {
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        imageUri = data.getData();
                        Glide.with(this)
                                .load(imageUri)
                                .circleCrop()
                                .into(imgHead);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String result = uploadAvatar();
                                Log.i("result",result);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if("成功".equals(result)){
                                            Toast.makeText(getContext(), "上传成功", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }).start();
                    }
                }
        );


    }
/**
 * 进行加载图片
 * */


    private String uploadAvatar() {
        String realPath = Utils.getRealPath(getContext(), imageUri);
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

    private void defineMediator() {
        TabLayoutMediator mediator = new TabLayoutMediator(
                mineFgTbl, mineFgVp2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position){
                    case 0:
                        tab.setText("发布");
                        break;
                    case 1:
                        tab.setText("评论");
                        break;
                    case 2:
                        tab.setText("收藏");
                        break;
                }
            }
        }
        );
        mediator.attach();
    }

    private void getViews(View page) {
        mineFgTbl = page.findViewById(R.id.mine_fg_tbl);
        mineFgVp2 = page.findViewById(R.id.mine_fg_vp2);
        imgHead = page.findViewById(R.id.mine_img);
        btnNavig = page.findViewById(R.id.mine_btn_navig);
        mineNav = page.findViewById(R.id.mine_fg_nav);
        mineDra = page.findViewById(R.id.mine_fg_dra);
        editData = page.findViewById(R.id.mine_fg_edit);
        name = page.findViewById(R.id.mine_fg_name);
        label = page.findViewById(R.id.mine_fg_label);
    }

    private void initPages() {
        fragments = new ArrayList<>();
        fragments.add(new IssueFragment());
        fragments.add(new CommentFragment());
        fragments.add(new CollectFragment());
    }
}
