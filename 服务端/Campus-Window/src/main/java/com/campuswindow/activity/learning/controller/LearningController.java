package com.campuswindow.activity.learning.controller;

import com.campuswindow.activity.learning.dto.LearningActivityDto;
import com.campuswindow.activity.learning.entity.LearningActivity;
import com.campuswindow.activity.learning.service.LearningService;
import com.campuswindow.fileupload.FileUploadService;
import com.campuswindow.utils.MinioConstant;
import com.campuswindow.utils.ResultVOUtil;
import com.campuswindow.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/activity/learning")
@Tag(name = "学术活动接口")
public class LearningController {

    private LearningService learningService;
    private FileUploadService fileUploadService;

    @GetMapping("/findAll")
    @Operation(summary = "查询所有数据")
    public Result findAll(){
        List<LearningActivity> activities = learningService.findAll();
        return ResultVOUtil.success(activities);
    }

    @PostMapping("/avatar")
    @Operation(summary = "上传文件")
    public Result<String> avatar(MultipartFile file) {
        String url = fileUploadService.save(file, MinioConstant.LEARNINGS_ROOT_PATH);
        return ResultVOUtil.success(url);
    }

    @PostMapping("/sendActivity")
    @Operation(summary = "发帖")
    public Result sendActivity(@RequestBody LearningActivityDto learningActivityDto) throws ParseException {
        learningService.sendActivity(learningActivityDto);
        return ResultVOUtil.success();
    }

    @GetMapping("/deleteActivity")
    @Operation(summary = "删帖")
    public Result deleteActivity(String activityId){
        learningService.deleteActivity(activityId);
        return ResultVOUtil.success();
    }

    @GetMapping("/selectActivity")
    @Operation(summary = "根据userId查询某个人的所有帖子")
    public Result selectActivity(String userId){
        List<LearningActivity> activities = learningService.selectActivity(userId);
        return ResultVOUtil.success(activities);
    }

    @Autowired
    public void setLearningService(LearningService learningService) {
        this.learningService = learningService;
    }
    @Autowired
    public void setFileUploadService(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }
}
