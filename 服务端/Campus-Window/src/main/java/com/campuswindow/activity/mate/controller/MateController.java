package com.campuswindow.activity.mate.controller;

import com.campuswindow.activity.mate.dto.MateActivityDto;
import com.campuswindow.activity.mate.entity.MateActivity;
import com.campuswindow.activity.mate.service.MateService;
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
@RequestMapping("/activity/mate")
@Tag(name = "搭子活动接口")
public class MateController {

    private MateService mateService;
    private FileUploadService fileUploadService;

    @GetMapping("/findAll")
    @Operation(summary = "查询所有数据")
    public Result findAll(){
        List<MateActivity> activities = mateService.findAll();
        return ResultVOUtil.success(activities);
    }

    @PostMapping("/avatar")
    @Operation(summary = "上传文件")
    public Result<String> avatar(MultipartFile file) {
        String url = fileUploadService.save(file, MinioConstant.MATES_ROOT_PATH);
        return ResultVOUtil.success(url);
    }

    @PostMapping("/sendActivity")
    @Operation(summary = "发帖")
    public Result sendActivity(@RequestBody MateActivityDto mateActivityDto) throws ParseException {
        MateActivity newMateActivity = mateService.sendActivity(mateActivityDto);
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
        List<MateActivity> activities = mateService.selectActivity(userId);
        return ResultVOUtil.success(activities);
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
