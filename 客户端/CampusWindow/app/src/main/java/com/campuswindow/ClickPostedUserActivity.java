package com.campuswindow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.campuswindow.adapter.ClickPostedUserActivityAdapter;
import com.campuswindow.chat.ChatDetailActivity;
import com.campuswindow.constant.UserConstant;
import com.campuswindow.entity.Activities;
import com.campuswindow.entity.OtherUserVo;
import com.campuswindow.entity.User;
import com.campuswindow.fragment.mine.CollectFragment;
import com.campuswindow.fragment.mine.CommentFragment;
import com.campuswindow.fragment.mine.IssueFragment;
import com.campuswindow.service.index.SearchOneSelfService;
import com.campuswindow.service.user.FollowUserService;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ClickPostedUserActivity extends AppCompatActivity {
    private ImageView personImgHeader;
    private TextView personFollowNum,personFanNum,personAwardNum,personFriendNum;
    private TextView personName,personLabel;
    private TabLayout personTbl;
    private ViewPager2 personVp2;
    private Button personAttention,personChat;
    private List<Fragment> fragments;
    private ClickPostedUserActivityAdapter clickPostedUserActivityAdapter;

    private Activities activity;
    private OtherUserVo otherUser;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_posted_user);
        activity = (Activities) getIntent().getSerializableExtra("activity");
        initPages();
        getViews();
        initValues();
        setListeners();

        clickPostedUserActivityAdapter = new ClickPostedUserActivityAdapter(fragments,this);
        personVp2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        personVp2.setAdapter(clickPostedUserActivityAdapter);
        defineMediator();
    }

    private void defineMediator() {
        TabLayoutMediator mediator = new TabLayoutMediator(
                personTbl, personVp2, new TabLayoutMediator.TabConfigurationStrategy() {
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

    private void initPages() {
        fragments = new ArrayList<>();
        fragments.add(new IssueFragment(activity.getUserId()));
        fragments.add(new CommentFragment(activity.getUserId()));
        fragments.add(new CollectFragment(activity.getUserId()));
    }

    private void setListeners() {
        //点击进行私聊
        personChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //获取自己本人的个人信息：
                        SearchOneSelfService service = new SearchOneSelfService();
                        Result result = service.getUserData();
                        System.out.println(result+"66666");
                        Gson gson = new Gson();
                        user = gson.fromJson(gson.toJson(result.getData()), User.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(ClickPostedUserActivity.this, ChatDetailActivity.class);
                                intent.putExtra("fromUserId", UserConstant.USER_ID);//from是个人
                                intent.putExtra("toUserId", activity.getUserId());//to是对象
                                intent.putExtra("toUserName", activity.getUserName());
                                intent.putExtra("toUserAvatar", activity.getAvatar());
                                intent.putExtra("fromUserName", user.getUserName());
                                intent.putExtra("fromUserAvatar", user.getAvatar());
                                startActivity(intent);
                            }
                        });
                    }
                }).start();
            }
        });
        //点击进行关注
        personAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("关注".equals(personAttention.getText().toString())){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            FollowUserService service = new FollowUserService();
                            Result result = service.followOtherUser(activity.getUserId());
                            if("成功".equals(result.getMsg())){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(ClickPostedUserActivity.this, "关注成功！", Toast.LENGTH_SHORT).show();
                                        personAttention.setText("取消关注");
                                    }
                                });
                            }
                        }
                    }).start();
                }else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            FollowUserService service = new FollowUserService();
                            Result result = service.cancelFollowOtherUser(activity.getUserId());
                            if("成功".equals(result.getMsg())){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(ClickPostedUserActivity.this, "取消关注成功！", Toast.LENGTH_SHORT).show();
                                        personAttention.setText("关注");
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }
        });

    }

    private void initValues() {
        personName.setText(activity.getUserName());
        Glide.with(getApplicationContext())
                .load(activity.getAvatar())
                .circleCrop()
                .into(personImgHeader);
        new Thread(new Runnable() {
            @Override
            public void run() {
                //获取发帖人的个人信息
                SearchOneSelfService service = new SearchOneSelfService();
                Result result = service.getOtherUserData(activity.getUserId());
                System.out.println(result+"66666");
                Gson gson = new Gson();
                otherUser = gson.fromJson(gson.toJson(result.getData()), OtherUserVo.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        personLabel.setText(otherUser.getSignature());
                        //TODO 个人的获取赞数、粉丝数等等，涉及到double、int的转换
                        if(otherUser.isFollowed()){
                            personAttention.setText("取消关注");
                        }else{
                            personAttention.setText("关注");
                        }
                    }
                });
            }
        }).start();
    }

    private void getViews() {
        personImgHeader = findViewById(R.id.person_img);
        personFanNum = findViewById(R.id.person_fg_fan_num);
        personFollowNum = findViewById(R.id.person_fg_follow_num);
        personAwardNum = findViewById(R.id.person_fg_award_num);
        personFriendNum = findViewById(R.id.person_fg_friend_num);
        personName = findViewById(R.id.person_fg_name);
        personLabel = findViewById(R.id.person_fg_label);
        personTbl = findViewById(R.id.person_fg_tbl);
        personVp2 = findViewById(R.id.person_fg_vp2);
        personAttention = findViewById(R.id.person_fg_attention);
        personChat = findViewById(R.id.person_fg_chat);
    }
}