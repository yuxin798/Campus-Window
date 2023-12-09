package com.campuswindow.service.user;

import android.util.Log;

import com.campuswindow.Result;
import com.campuswindow.entity.PasswordDto;
import com.campuswindow.entity.User;
import com.campuswindow.server.API;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ResetPwdService {
    private OkHttpClient client;
    private Request request;
    private Call call;
    private Response response;

    public Result verify(User user) {
        client = new OkHttpClient();
//        String emailJson = new Gson().toJson(user);
//        RequestBody body = RequestBody.create(
//                MediaType.parse("application/json;charset=utf-8"),
//                emailJson
//        );
        //sendEmailForUpdatePassword

        request = new Request.Builder()
                .url(API.SERVER_URL+"sendEmailForUpdatePassword?to=" + user.getEmail())
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

    public Result sendRtPwd(PasswordDto passwordDto) {
        client = new OkHttpClient();
        String  userJson = new Gson().toJson(passwordDto);
        request = new Request.Builder()
                .url(API.SERVER_URL + "updatePassword")
                .post(RequestBody.create(
                        MediaType.parse("application/json;charset=utf-8"),
                        userJson)
                ).build();
        call = client.newCall(request);
        try {
            response = call.execute();
            String string = response.body().string();
            Gson gson = new Gson();
            Result result = gson.fromJson(string, Result.class);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
