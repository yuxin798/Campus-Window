package com.campuswindow.service.user;

import android.util.Log;

import com.campuswindow.LoginActivity;
import com.campuswindow.Result;
import com.campuswindow.entity.User;
import com.campuswindow.server.API;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginService {
    private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    public static Result login(String email, String pwd, LoginActivity loginActivity) {
        OkHttpClient client = new OkHttpClient();
        User user = new User();
        user.setEmail(email);
        user.setPassword(pwd);
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        Log.i("user:",user.toString());
        RequestBody body = RequestBody.create(JSON,userJson);
        Log.i("body",body.toString());
        Request request  = new Request.Builder()
                .url(API.USER + "login")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            //处理响应 TODO 处理响应
            String string = response.body().string();
            Log.i("string:",string);
            Result result = gson.fromJson(string, Result.class);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
