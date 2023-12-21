package com.campuswindow.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.campuswindow.AcademicItemDetailActivity;
import com.campuswindow.R;
import com.campuswindow.Result;
import com.campuswindow.adapter.CommentFragmentAdapter;
import com.campuswindow.constant.UserConstant;
import com.campuswindow.entity.Activities;
import com.campuswindow.entity.CommentUserVo;
import com.campuswindow.entity.User;
import com.campuswindow.interfaces.OnCommentItemClickListener;
import com.campuswindow.service.index.SearchOneSelfService;
import com.campuswindow.service.mine.CommentService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CommentFragment extends Fragment {
    private RecyclerView commentRy;
    private CommentFragmentAdapter commentFragmentAdapter;
    private List<CommentUserVo> commentList = new ArrayList<>();
    private Handler handler;
    private User user;
    private Activities activitys;

    private String userId;

    public CommentFragment(){
        handler = new Handler(Looper.getMainLooper());
    }

    public CommentFragment(String userId) {
        this.userId = userId;
        handler = new Handler(Looper.getMainLooper());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.mine_comment_fragment,null);
        commentRy = page.findViewById(R.id.mine_comment_fg_ry);
        if(!UserConstant.USER_ID.equals(userId)){
            getCommentAndUserList(userId);
        }else{
            getCommentAndUserList();
        }

        return page;
    }

    private void getCommentAndUserList(String userId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //TODO getCommentAndUserList()分别写了两个方法，可以重用。
                CommentService service = new CommentService();
                Result result = service.getCommentListByUserId(userId);
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<CommentUserVo>>(){}.getType();
                commentList.addAll(gson.fromJson(gson.toJson(result.getData()), type));
                //获取用户信息
                SearchOneSelfService service2 = new SearchOneSelfService();
                Result result2 = service2.getUserData();
                System.out.println(result2+"66666"+commentList);
                user = gson.fromJson(gson.toJson(result2.getData()), User.class);
                runOnMainThread();
            }
        }).start();
    }

    private void getCommentAndUserList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CommentService service = new CommentService();
                Result result = service.getCommentList();
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<CommentUserVo>>(){}.getType();
                commentList.addAll(gson.fromJson(gson.toJson(result.getData()), type));
                //获取用户信息
                SearchOneSelfService service2 = new SearchOneSelfService();
                Result result2 = service2.getUserData();
                System.out.println(result2+"66666"+commentList);
                user = gson.fromJson(gson.toJson(result2.getData()), User.class);
                runOnMainThread();
            }
        }).start();
    }

    private void runOnMainThread() {
        // 在主线程上执行一段代码
        handler.post(new Runnable() {
            @Override
            public void run() {
                commentFragmentAdapter = new CommentFragmentAdapter(commentList,user,getContext());
                commentRy.setAdapter(commentFragmentAdapter);
                commentRy.setLayoutManager(new LinearLayoutManager(getContext()));
                commentFragmentAdapter.setCommentList(commentList);
                commentFragmentAdapter.setUser(user);
                commentFragmentAdapter.notifyDataSetChanged();
                System.out.println("66666666666"+commentList.size());
                commentFragmentAdapter.setOnItemClickListener(new OnCommentItemClickListener() {
                    @Override
                    public void onItemClick(List<CommentUserVo> commentList, View view, int position) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                CommentService service = new CommentService();
                                Result activity = service.getOneActivityByActivityId(commentList.get(position).getActivityId());
                                Gson gson = new Gson();
                                activitys = gson.fromJson(gson.toJson(activity.getData()),Activities.class);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(getContext(), AcademicItemDetailActivity.class);
                                        intent.putExtra("data",activitys);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }).start();
                    }
                });
            }
        });
    }
}
