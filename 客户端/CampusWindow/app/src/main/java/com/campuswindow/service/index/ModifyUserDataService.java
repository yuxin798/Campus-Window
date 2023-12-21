package com.campuswindow.service.index;

import android.util.Log;

import com.campuswindow.Result;
import com.campuswindow.entity.ModifyInformationDto;
import com.campuswindow.server.API;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ModifyUserDataService {
    private OkHttpClient client;
    private RequestBody body;
    private Request request;
    private Call call;
    private Response response;

    //修改个人信息
    public Result getUserData(ModifyInformationDto modifyInformationDto){
        client = new OkHttpClient();
        String s = new Gson().toJson(modifyInformationDto);
        body = RequestBody.create(
                MediaType.parse("application/json;charset=utf-8")
                ,s);
        request = new Request.Builder()
                .url(API.USER_MODIFY_INFO)
                .post(body)
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
