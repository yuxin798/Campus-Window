package com.campuswindow.activity.mate.controller;

import com.campuswindow.activity.mate.dto.MateActivityDto;
import com.campuswindow.activity.mate.service.MateService;
import com.campuswindow.activity.mate.vo.MateActivityVo;
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
@RequestMapping("/activity/mate")
@Tag(name = "搭子活动接口")
public class MateController {

    private MateService mateService;
    private FileUploadService fileUploadService;

    @GetMapping("/findAll")
    @Operation(summary = "查询所有数据")
    public Result findAll(){
        List<MateActivityVo> activities = mateService.findAll();
        return ResultVOUtil.success(activities);
    }
    @PostMapping("/sendActivity")
    @Operation(summary = "发帖")
    public Result sendActivity(@RequestBody MateActivityDto mateActivityDto) throws ParseException {
        mateService.sendActivity(mateActivityDto);
        return ResultVOUtil.success();
    }

    @GetMapping("/deleteActivity")
    @Operation(summary = "删帖")
    public Result deleteActivity(String activityId){
        mateService.deleteActivity(activityId);
        return ResultVOUtil.success();
    }

    @GetMapping("/selectActivity")
    @Operation(summary = "根据userId查询某个人的所有帖子")
    public Result selectActivity(String userId){
        List<MateActivityVo> activities = mateService.selectActivity(userId);
        return ResultVOUtil.success(activities);
    }

    @GetMapping("/addLove")
    @Operation(summary = "点赞")
    public Result addLove(String activityId){
        mateService.addLove(activityId);
        return ResultVOUtil.success();
    }

    @GetMapping("/decreaseLove")
    @Operation(summary = "取消点赞")
    public Result decreaseLove(String activityId){
        mateService.decreaseLove(activityId);
        return ResultVOUtil.success();
    }

    @Autowired
    public void setMateService(MateService mateService) {
        this.mateService = mateService;
    }
    @Autowired
    public void setFileUploadService(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }
}
