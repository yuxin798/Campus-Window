package com.campuswindow.service.user;

import android.util.Log;

import com.campuswindow.Result;
import com.campuswindow.entity.User;
import com.campuswindow.server.API;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RegisterService {
    private OkHttpClient client;
    private Request request;
    private Call call;
    private Response response;

    public Result register(User user) {
        client = new OkHttpClient();
        String userJson = new Gson().toJson(user);
        Log.i("user3:", user.toString());
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json;charset=utf-8"),
                userJson
        );
        request = new Request.Builder()
                .post(body)
                .url(API.SERVER_URL + "register")
                .build();
        call = client.newCall(request);
        try {
            response = call.execute();
            //注册响应 TODO 获取注册响应
//            Log.i("response:", response.body().string());
            Gson gson = new Gson();
            String string = response.body().string();
            Result result = gson.fromJson(string, Result.class);
            Log.i("Result:", result.toString());
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Result verify(User user) {
        client = new OkHttpClient();
//        String emailJson = new Gson().toJson(user);
//        RequestBody body = RequestBody.create(
//                MediaType.parse("application/json;charset=utf-8"),
//                emailJson
//        );

        request = new Request.Builder()
                .url(API.SERVER_URL+"sendEmail?to=" + user.getEmail())
                .get()
                .build();
        call = client.newCall(request);
        try {
            response = call.execute();
            //接收验证码：
            Gson gson = new Gson();
            Result result = gson.fromJson(response.body().string(), Result.class);

            Log.i("result",result.toString());
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
