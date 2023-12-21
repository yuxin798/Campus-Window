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
import com.campuswindow.adapter.CollectFragmentAdapter;
import com.campuswindow.constant.UserConstant;
import com.campuswindow.entity.Activities;
import com.campuswindow.interfaces.OnItemClickListener;
import com.campuswindow.service.mine.CollectService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CollectFragment extends Fragment{
    private RecyclerView collectRy;
    private CollectFragmentAdapter collectFragmentAdapter;
    private Handler handler;
    private List<Activities> collectList = new ArrayList<>();

    private String userId;

    public CollectFragment() {
        handler = new Handler(Looper.getMainLooper());
    }

    public CollectFragment(String userId) {
        this.userId = userId;
        handler = new Handler(Looper.getMainLooper());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.mine_collect_fragment,null);
        collectRy = page.findViewById(R.id.mine_collect_fg_ry);
        if(!UserConstant.USER_ID.equals(userId)){
            getCollectList(userId);
        }else{
            getCollectList();
        }

        return page;
    }

    private void getCollectList(String userId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CollectService service = new CollectService();
                Result result = service.getCollectListByUserId(userId);
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Activities>>(){}.getType();
                collectList.addAll(gson.fromJson(gson.toJson(result.getData()), type));
                runOnMainThread();
            }
        }).start();
    }

    private void getCollectList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CollectService service = new CollectService();
                Result result = service.getCollectList();
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Activities>>(){}.getType();
                collectList.addAll(gson.fromJson(gson.toJson(result.getData()), type));
                runOnMainThread();
            }
        }).start();
    }

    private void runOnMainThread() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                collectFragmentAdapter = new CollectFragmentAdapter(collectList,getContext());
                collectRy.setAdapter(collectFragmentAdapter);
                collectRy.setLayoutManager(new LinearLayoutManager(getContext()));
                collectFragmentAdapter.setCollectList(collectList);
                collectFragmentAdapter.notifyDataSetChanged();
                collectFragmentAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(List<Activities> collectList, View view, int position) {
                        Intent intent = new Intent(getContext(), AcademicItemDetailActivity.class);
                        intent.putExtra("data",collectList.get(position));
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
