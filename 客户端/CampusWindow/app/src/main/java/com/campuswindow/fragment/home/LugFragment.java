package com.campuswindow.fragment.home;

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

import com.campuswindow.LugItemDetailActivity;
import com.campuswindow.R;
import com.campuswindow.Result;
import com.campuswindow.adapter.AcademicFragmentListAdapter;
import com.campuswindow.entity.Activities;
import com.campuswindow.service.home.LugService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LugFragment extends Fragment {
    private RecyclerView lugRe;
    private List<Activities> lugList = new ArrayList<>();
//    private LugFragmentListAdapter lugFragmentListAdapter;
    private AcademicFragmentListAdapter lugFragmentListAdapter;

    private Handler handler;
    public LugFragment() {
        handler = new Handler(Looper.getMainLooper());
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.index_lug_fragment,null);
        initPage(page);
        getLugList();
        return page;
    }

    private void getLugList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LugService lugService = new LugService();
                Result result = lugService.getLugList();
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Activities>>(){}.getType();
                lugList = gson.fromJson(gson.toJson(result.getData()), type);
                Log.i("lugList",lugList.toString());
                runOnMainThread();
            }
        }).start();

    }

    private void runOnMainThread() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                lugFragmentListAdapter = new AcademicFragmentListAdapter(lugList,getContext());
                lugRe.setAdapter(lugFragmentListAdapter);
                lugRe.setLayoutManager(new LinearLayoutManager(getContext()));
                lugFragmentListAdapter.setAcademicList(lugList);
                lugFragmentListAdapter.setOnItemClickListener(new AcademicFragmentListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getContext(), LugItemDetailActivity.class);
                        intent.putExtra("data",lugList.get(position));
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void initPage(View page) {
        lugRe = page.findViewById(R.id.lug_re);
    }
}
