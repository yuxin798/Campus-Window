package com.campuswindow.activity.learning.controller;

import com.campuswindow.activity.learning.dto.LearningActivityDto;
import com.campuswindow.activity.learning.service.LearningService;
import com.campuswindow.activity.learning.vo.LearningActivityVo;
import com.campuswindow.fileupload.FileUploadService;
import com.campuswindow.utils.ResultVOUtil;
import com.campuswindow.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result<List<LearningActivityVo>> findAll(){
        List<LearningActivityVo> activities = learningService.findAll();
        return ResultVOUtil.success(activities);
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
    public Result<List<LearningActivityVo>> selectActivity(String userId){
        List<LearningActivityVo> activities = learningService.selectActivity(userId);
        return ResultVOUtil.success(activities);
    }

    @GetMapping("/addLove")
    @Operation(summary = "点赞")
    public Result addLove(String activityId){
        learningService.addLove(activityId);
        return ResultVOUtil.success();
    }

    @GetMapping("/decreaseLove")
    @Operation(summary = "取消点赞")
    public Result decreaseLove(String activityId){
        learningService.decreaseLove(activityId);
        return ResultVOUtil.success();
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
