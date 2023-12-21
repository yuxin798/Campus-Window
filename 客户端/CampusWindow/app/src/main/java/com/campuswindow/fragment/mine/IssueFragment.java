package com.campuswindow.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
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
import com.campuswindow.adapter.AcademicFragmentListAdapter;
import com.campuswindow.constant.UserConstant;
import com.campuswindow.entity.Activities;
import com.campuswindow.service.mine.IssueService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class IssueFragment extends Fragment{
    private RecyclerView issueRy;
    private List<Activities> issueList = new ArrayList<>();
    private AcademicFragmentListAdapter academicFragmentListAdapter;

    private String userId;

    private Handler handler;

    public IssueFragment() {
        handler = new Handler(Looper.getMainLooper());
    }

    public IssueFragment(String userId) {
        this.userId = userId;
        handler = new Handler(Looper.getMainLooper());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.mine_all_fragment,null);
        issueRy = page.findViewById(R.id.mine_all_fg_ry);
        if( !UserConstant.USER_ID.equals(userId)){
            getIssueList(userId);
        }else{
            getIssueList(UserConstant.USER_ID);
        }

        return page;
    }

    private void getIssueList(String userId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                IssueService service = new IssueService();
                Result result = service.getIssueListByUserId(userId);
                Log.i("result",result.toString());
                Log.i("result.getData():",result.getData().toString());
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Activities>>(){}.getType();
                issueList.addAll(gson.fromJson(gson.toJson(result.getData()), type));
                Log.i("academicList:",issueList.toString());
                Log.i("academicList:",issueList.toString().isEmpty()+"");
                runOnMainThread();
            }
        }).start();
    }

    private void runOnMainThread() {
        // 在主线程上执行一段代码
        handler.post(new Runnable() {
            @Override
            public void run() {
                academicFragmentListAdapter = new AcademicFragmentListAdapter(issueList,getContext());
                issueRy.setAdapter(academicFragmentListAdapter);
                issueRy.setLayoutManager(new LinearLayoutManager(getContext()));
                academicFragmentListAdapter.setAcademicList(issueList);
                academicFragmentListAdapter.notifyDataSetChanged();
                System.out.println("66666666666"+issueList.size());
                academicFragmentListAdapter.setOnItemClickListener(new AcademicFragmentListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getContext(), AcademicItemDetailActivity.class);
                        intent.putExtra("data",issueList.get(position));
                        startActivity(intent);
                    }
                });
            }
        });
    }

}
