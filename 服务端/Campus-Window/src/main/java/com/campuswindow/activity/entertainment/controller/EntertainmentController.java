package com.campuswindow.activity.entertainment.controller;

import com.campuswindow.activity.entertainment.entity.EntertainmentActivity;
import com.campuswindow.activity.entertainment.service.EntertainmentService;
import com.campuswindow.fileupload.FileUploadService;
import com.campuswindow.utils.ResultVOUtil;
import com.campuswindow.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/activity/entertainment")
@Tag(name = "娱乐活动接口")
public class EntertainmentController {

    @Autowired
    private EntertainmentService service;
    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping("/findAll")
    @Operation(summary = "查询所有数据")
    public Result findAll(){
        List<EntertainmentActivity> activities = service.findAll();
        if (activities == null){
            throw new RuntimeException("网络异常，请稍后重试");
        }
        return ResultVOUtil.success(activities);
    }
    @PostMapping("/sendActivity")
    @Operation(summary = "发帖")
    public Result sendActivity(EntertainmentActivity entertainmentActivity, MultipartFile[] avatars) throws ParseException {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(avatars)
                .filter(avatar -> !avatar.isEmpty())
                .forEach(avatar -> fileUploadService.save(avatar));
        entertainmentActivity.setActivityImages(builder.toString());
        EntertainmentActivity newEntertainmentActivity = service.sendActivity(entertainmentActivity);
        if (newEntertainmentActivity == null){
            throw new RuntimeException("发帖失败");
        }
        return ResultVOUtil.success("发帖成功");
    }

    @GetMapping("/deleteActivity")
    @Operation(summary = "删帖")
    public Result deleteActivity(@NotNull(message = "活动Id不能为空") String activityId){
//        if (activityId == null){
//            throw new RuntimeException("失败");
//        }
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
