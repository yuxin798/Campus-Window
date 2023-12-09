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

import com.campuswindow.AcademicItemDetailActivity;
import com.campuswindow.R;
import com.campuswindow.Result;
import com.campuswindow.adapter.AcademicFragmentListAdapter;
import com.campuswindow.entity.Activities;
import com.campuswindow.service.home.AcademicService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class AcademicActivityFragment extends Fragment {
    private List<Activities> academicList = new ArrayList<>();
    private RecyclerView academicRe;
    private AcademicFragmentListAdapter academicFragmentListAdapter;

    private Handler handler;

    public AcademicActivityFragment() {
        handler = new Handler(Looper.getMainLooper());
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.index_academic_fragment,null);
        //初始化页面
        initPage(page);
        //获取数据源
        getAcademicList();
//        listViewListenerMethod();

        return page;
    }

    //获取数据源:
    private void getAcademicList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AcademicService service = new AcademicService();
                Result result = service.getAcademicList();
                Log.i("result",result.toString());
                Log.i("result.getData():",result.getData().toString());
//                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Activities>>(){}.getType();

                academicList = gson.fromJson(gson.toJson(result.getData()), type);
                runOnMainThread();
                Log.i("academicList:",academicList.toString());
                Log.i("academicList:",academicList.toString().isEmpty()+"");
            }
        }).start();
    }
    public void runOnMainThread() {
        // 在主线程上执行一段代码
        handler.post(new Runnable() {
            @Override
            public void run() {
//                academicFragmentListAdapter.notifyDataSetChanged();
                academicFragmentListAdapter = new AcademicFragmentListAdapter(academicList,getContext());
                academicRe.setAdapter(academicFragmentListAdapter);
                academicRe.setLayoutManager(new LinearLayoutManager(getContext()));
                System.out.println("66666666666"+academicList.size());
                academicFragmentListAdapter.setOnItemClickListener(new AcademicFragmentListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getContext(),AcademicItemDetailActivity.class);
                        intent.putExtra("data",academicList.get(position));
                        startActivity(intent);
                    }
                });

            }
        });
    }

    private void initPage(View page) {
//        academicLv = page.findViewById(R.id.academic_lv);
        academicRe = page.findViewById(R.id.academic_re);
    }
}
