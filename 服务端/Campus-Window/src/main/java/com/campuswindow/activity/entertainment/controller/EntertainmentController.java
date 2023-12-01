package com.campuswindow.activity.entertainment.controller;

import com.campuswindow.activity.entertainment.dto.EntertainmentActivityDto;
import com.campuswindow.activity.entertainment.entity.EntertainmentActivity;
import com.campuswindow.activity.entertainment.service.EntertainmentService;
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
@RequestMapping("/activity/entertainment")
@Tag(name = "娱乐活动接口")
public class EntertainmentController {

    private EntertainmentService entertainmentService;
    private FileUploadService fileUploadService;

    @GetMapping("/findAll")
    @Operation(summary = "查询所有数据")
    public Result findAll(){
        List<EntertainmentActivity> activities = entertainmentService.findAll();
        return ResultVOUtil.success(activities);
    }

    @PostMapping("/avatar")
    @Operation(summary = "上传文件")
    public Result<String> avatar(MultipartFile file) {
        String url =  fileUploadService.save(file, MinioConstant.ENTERTAINMENTS_ROOT_PATH);
        return ResultVOUtil.success(url);
    }

    @PostMapping("/sendActivity")
    @Operation(summary = "发帖")
    public Result sendActivity(@RequestBody EntertainmentActivityDto entertainmentActivityDto) throws ParseException {
        entertainmentService.sendActivity(entertainmentActivityDto);
        return ResultVOUtil.success();
    }

    @GetMapping("/deleteActivity")
    @Operation(summary = "删帖")
    public Result deleteActivity(String activityId){
        entertainmentService.deleteActivity(activityId);
        return ResultVOUtil.success();
    }

    @GetMapping("/selectActivity")
    @Operation(summary = "根据userId查询某个人的所有帖子")
    public Result selectActivity(String userId){
        List<EntertainmentActivity> activities = entertainmentService.selectActivity(userId);
        return ResultVOUtil.success(activities);
    }
    @Autowired
    public void setEntertainmentService(EntertainmentService entertainmentService) {
        this.entertainmentService = entertainmentService;
    }

    @Autowired
    public void setFileUploadService(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }
}
