package com.campuswindow.service.mine;

import android.util.Log;

import com.campuswindow.Result;
import com.campuswindow.constant.UserConstant;
import com.campuswindow.entity.CommentUserVo;
import com.campuswindow.server.API;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CommentService {
    private OkHttpClient client;
    private Request request;
    private Call call;
    private Response response;

    //获取数据源
    public Result getCommentList(){
        client = new OkHttpClient();
        request = new Request.Builder()
                .url(API.ACTIVITY + "comment/findAllComments?userId="+ UserConstant.USER_ID)
                .get()
                .build();
        call = client.newCall(request);
        try {
            response = call.execute();
            Gson gson = new Gson();
            Result result = gson.fromJson(response.body().string(), new TypeToken<Result<List<CommentUserVo>>>() {}.getType());
            Log.i("result666666",result.toString());
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //点击某条评论获取该评论的 主贴 :
    public Result getOneActivityByActivityId(String activityId){
        client = new OkHttpClient();
        request = new Request.Builder()
                .url(API.ACTIVITY + "findActivityDetails/" + UserConstant.USER_ID + "/" + activityId)
                .get()
                .build();
        call = client.newCall(request);
        try {
            response = call.execute();
            Gson gson = new Gson();
            Result result = gson.fromJson(response.body().string(),Result.class);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Result getCommentListByUserId(String userId) {
        client = new OkHttpClient();
        request = new Request.Builder()
                .url(API.ACTIVITY + "comment/findAllComments?userId="+ userId)
                .get()
                .build();
        call = client.newCall(request);
        try {
            response = call.execute();
            Gson gson = new Gson();
            Result result = gson.fromJson(response.body().string(), new TypeToken<Result<List<CommentUserVo>>>() {}.getType());
            Log.i("result666666",result.toString());
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
