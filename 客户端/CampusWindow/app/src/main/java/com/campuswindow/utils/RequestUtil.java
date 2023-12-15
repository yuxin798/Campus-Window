package com.campuswindow.utils;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.Response;

public class RequestUtil {
    private static Gson gson;

    static  {
        gson = new Gson();
    }

    public static Response submit(Callable<Response> callable) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Response> future = executorService.submit(callable);
        try {
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromResponse(Response response, Type type) {
        try {
            return gson.fromJson(response.body().string(), type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
