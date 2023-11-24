package com.campuswindow.activity.mate.controller;

import com.campuswindow.activity.mate.entity.MateActivity;
import com.campuswindow.activity.mate.service.MateService;
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
@RequestMapping("/activity/mate")
@Tag(name = "搭子活动接口")
public class MateController {

    @Autowired
    private MateService service;

    @GetMapping("/findAll")
    @Operation(summary = "查询所有数据")
    public Result findAll(){
        List<MateActivity> activities = service.findAll();
        if (activities == null){
            return ResultVOUtil.error("网络异常，请稍后重试");
        }
        return ResultVOUtil.success(activities);
    }
    @PostMapping("/sendActivity")
    @Operation(summary = "发帖")
    public Result sendActivity(MateActivity mateActivity, MultipartFile[] avatars) throws ParseException {
        StringBuilder builder = new StringBuilder();
        try {
            for (int i = 0; i < avatars.length ;i++){
                if (avatars[i].isEmpty()){
                    break;
                }
                String fileName = avatars[i].getOriginalFilename();
                String suffix = fileName.substring(fileName.indexOf("."));
                String  filePath = "D:\\images\\mates\\" + mateActivity.getUserId() + i  + suffix;
                avatars[i].transferTo(new File(filePath));
                if (i == 0){
                    builder.append(filePath);
                }else {
                    builder.append("|" + filePath);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mateActivity.setActivityImages(builder.toString());
        MateActivity newMateActivity = service.sendActivity(mateActivity);
        if (newMateActivity == null){
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
        List<MateActivity> activities = service.selectActivity(userId);
        return ResultVOUtil.success(activities);
    }
}
