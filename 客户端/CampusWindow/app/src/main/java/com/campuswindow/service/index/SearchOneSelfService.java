package com.campuswindow.service.index;

import android.util.Log;

import com.campuswindow.Result;
import com.campuswindow.constant.UserConstant;
import com.campuswindow.server.API;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchOneSelfService {
    private OkHttpClient client;
    private Request request;
    private Call call;
    private Response response;

    //获取数据源
    public Result getUserData(){
        client = new OkHttpClient();
        request = new Request.Builder()
                .url(API.USER_FIND_INFO + UserConstant.USER_ID)
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
