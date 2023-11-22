package com.campuswindow.activity.controller;

import com.campuswindow.activity.entity.Activity;
import com.campuswindow.activity.service.ActivityService;
import com.campuswindow.utils.ResultVOUtil;
import com.campuswindow.vo.Result;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService service;

    @GetMapping("/findAll")
    public Result findAll(){
        List<Activity> activities = service.findAll();
        if (activities == null){
            return ResultVOUtil.error("网络异常，请稍后重试");
        }
        return ResultVOUtil.success(activities);
    }
    @PostMapping("/sendActivity")
    public Result sendActivity(@RequestBody Activity activity, MultipartFile[] avatars){
        //TODO 图片上传
        Activity newActivity = service.sendActivity(activity);
        if (activity == null){
            return ResultVOUtil.error("网络异常，请稍后重试");
        }
        return ResultVOUtil.success("发帖成功");
    }

    @GetMapping("/selectActivity")
    public Result selectActivity(@RequestBody String userId){
        List<Activity> activities = service.selectActivity(userId);
        if (activities == null){
            return ResultVOUtil.error("网络异常，请稍后重试");
        }
        return ResultVOUtil.success(activities);
    }
}
