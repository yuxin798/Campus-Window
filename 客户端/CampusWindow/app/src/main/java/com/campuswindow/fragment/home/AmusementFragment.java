package com.campuswindow.fragment.home;

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

import com.campuswindow.AmusementItemDetailActivity;
import com.campuswindow.R;
import com.campuswindow.Result;
import com.campuswindow.adapter.AcademicFragmentListAdapter;
import com.campuswindow.entity.Activities;
import com.campuswindow.service.home.AmusementService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AmusementFragment extends Fragment {
    private RecyclerView amusementRe;
    private List<Activities> amusementList = new ArrayList<>();
//    private AmusementFragmentListAdapter amusementFragmentListAdapter;
    private AcademicFragmentListAdapter amusementFragmentListAdapter;

    private Handler handler;

    public AmusementFragment() {
        handler = new Handler(Looper.getMainLooper());
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.index_amusement_fragment,null);
        initPage(page);
        getAmusementList();
        return page;
    }

    private void getAmusementList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AmusementService amusementService = new AmusementService();
                Result result = amusementService.getAmusementList();
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Activities>>(){}.getType();

                amusementList = gson.fromJson(gson.toJson(result.getData()), type);
                runOnMainThread();
            }
        }).start();
    }

    private void runOnMainThread() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                amusementFragmentListAdapter = new AcademicFragmentListAdapter(amusementList,getContext());
                amusementRe.setAdapter(amusementFragmentListAdapter);
                amusementRe.setLayoutManager(new LinearLayoutManager(getContext()));
                amusementFragmentListAdapter.setOnItemClickListener(new AcademicFragmentListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getContext(), AmusementItemDetailActivity.class);
                        intent.putExtra("data",amusementList.get(position));
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void initPage(View page) {
        amusementRe = page.findViewById(R.id.amusement_re);
    }
}
