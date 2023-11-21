package com.campuswindow.utils;


import com.campuswindow.vo.Result;

public class ResultVOUtil {
    public static<T> Result success(T data){
        Result<T> result = new Result<>();
        result.setCode("200");
        result.setMsg("成功");
        result.setData(data);
        return result;
    }
    public static<T> Result success(String msg){
        Result<T> result = new Result<>();
        result.setCode("200");
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static<T> Result error(String msg){
        Result<T> result = new Result<>();
        result.setCode("500");
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}
