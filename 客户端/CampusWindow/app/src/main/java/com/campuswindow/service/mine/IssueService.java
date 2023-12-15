package com.campuswindow.service.mine;

import android.util.Log;

import com.campuswindow.Result;
import com.campuswindow.constant.UserConstant;
import com.campuswindow.entity.Activities;
import com.campuswindow.server.API;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IssueService {
    private OkHttpClient client;
    private Request request;
    private Call call;
    private Response response;

    //获取数据源
    public Result getIssueList(){
        client = new OkHttpClient();
        request = new Request.Builder()
                .url(API.ACTIVITY + "findAllByType?type=0&userId="+ UserConstant.USER_ID)
                .get()
                .build();
        call = client.newCall(request);
        try {
            response = call.execute();
            Gson gson = new Gson();
            Result result = gson.fromJson(response.body().string(), new TypeToken<Result<List<Activities>>>() {}.getType());
            Log.i("result",result.toString());
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
