package com.campuswindow.service.home;

import android.util.Log;

import com.campuswindow.Result;
import com.campuswindow.server.API;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchService {
    private OkHttpClient client;
    private Request request;
    private Call call;
    private Response response;

    //获取单个数据源（模糊查询）
    public Result getActivityList(String name){
        client = new OkHttpClient();
        request = new Request.Builder()
                .url(API.ACTIVITY_FIND_SEARCH +"?activityTitle="+ name)
                .get()
                .build();
        call = client.newCall(request);
        try {
            response = call.execute();
            Gson gson = new Gson();
            Result result = gson.fromJson(response.body().string(),Result.class);
            Log.i("result",result.toString());
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //获取所有数据源（全部显示）
    public Result getActivitiesList(){
        client = new OkHttpClient();
        request = new Request.Builder()
                .url(API.ACTIVITY_FIND_SEARCH)
                .get()
                .build();
        call = client.newCall(request);
        try {
            response = call.execute();
            Gson gson = new Gson();
            Result result = gson.fromJson(response.body().string(),Result.class);
            Log.i("result",result.toString());
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
