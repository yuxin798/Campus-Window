package com.campuswindow.activity.entertainment.controller;

import com.campuswindow.activity.entertainment.service.EntertainmentService;
import com.campuswindow.activity.entertainment.entity.EntertainmentActivity;
import com.campuswindow.utils.ResultVOUtil;
import com.campuswindow.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/activity/entertainment")
@Tag(name = "娱乐活动接口")
public class EntertainmentController {

    @Autowired
    private EntertainmentService service;

    @GetMapping("/findAll")
    @Operation(summary = "查询所有数据")
    public Result findAll(){
        List<EntertainmentActivity> activities = service.findAll();
        if (activities == null){
            return ResultVOUtil.error("网络异常，请稍后重试");
        }
        return ResultVOUtil.success(activities);
    }
    @PostMapping("/sendActivity")
    @Operation(summary = "发帖")
    public Result sendActivity(EntertainmentActivity entertainmentActivity, MultipartFile[] avatars) throws ParseException {
        StringBuilder builder = new StringBuilder();
        try {
            for (int i = 0; i < avatars.length; i++) {
                if (avatars[i].isEmpty()){
                    break;
                }
                String fileName = avatars[i].getOriginalFilename();
                String suffix = fileName.substring(fileName.indexOf("."));
                String filePath = "D:\\images\\entertainments\\" + entertainmentActivity.getUserId() + i + suffix;
                avatars[i].transferTo(new File(filePath));
                if (i == 0) {
                    builder.append(filePath);
                } else {
                    builder.append("|" + filePath);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        entertainmentActivity.setActivityImages(builder.toString());
        EntertainmentActivity newEntertainmentActivity = service.sendActivity(entertainmentActivity);
        if (newEntertainmentActivity == null){
            return ResultVOUtil.error("发帖失败");
        }
        return ResultVOUtil.success("发帖成功");
    }

    @GetMapping("/deleteActivity")
    @Operation(summary = "删帖")
    public Result deleteActivity(String activityId){
        if (activityId == null){
            return ResultVOUtil.error("失败");
        }
        service.deleteActivity(activityId);
        return ResultVOUtil.success("成功");
    }

    @GetMapping("/selectActivity")
    @Operation(summary = "根据userId查询某个人的所有帖子")
    public Result selectActivity(String userId){
        List<EntertainmentActivity> activities = service.selectActivity(userId);
        return ResultVOUtil.success(activities);
    }
}
