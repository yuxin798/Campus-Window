package com.campuswindow.activity.entertainment.controller;

import com.campuswindow.activity.entertainment.dto.EntertainmentActivityDto;
import com.campuswindow.activity.entertainment.service.EntertainmentService;
import com.campuswindow.activity.entertainment.vo.EntertainmentActivityVo;
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
@RequestMapping("/activity/entertainment")
@Tag(name = "娱乐活动接口")
public class EntertainmentController {

    private EntertainmentService entertainmentService;
    private FileUploadService fileUploadService;

    /*
     * 查询所有娱乐帖子，根据发表时间降序排序
     */
    @GetMapping("/findAll")
    @Operation(summary = "查询所有娱乐帖子")
    public Result findAll(){
        List<EntertainmentActivityVo> activities = entertainmentService.findAll();
        return ResultVOUtil.success(activities);
    }

    /*
     * 发帖，同时将图片或视频网络地址保存到数据库中
     */
    @PostMapping("/sendActivity")
    @Operation(summary = "发帖")
    public Result sendActivity(@RequestBody EntertainmentActivityDto entertainmentActivityDto) throws ParseException {
        entertainmentService.sendActivity(entertainmentActivityDto);
        return ResultVOUtil.success();
    }

    /*
     * 根据帖子id删除帖子
     */
    @GetMapping("/deleteActivity")
    @Operation(summary = "根据帖子id删除帖子")
    public Result deleteActivity(String activityId){
        entertainmentService.deleteActivity(activityId);
        return ResultVOUtil.success();
    }

    /*
     * 根据userId查询某个人的所有帖子
     */
    @GetMapping("/selectActivity")
    @Operation(summary = "根据userId查询某个人的所有帖子")
    public Result<List<EntertainmentActivityVo>> selectActivity(String userId){
        List<EntertainmentActivityVo> activities = entertainmentService.selectActivity(userId);
        return ResultVOUtil.success(activities);
    }

    /*
     * 帖子点赞
     */
    @GetMapping("/addLove")
    @Operation(summary = "点赞")
    public Result addLove(String activityId){
        entertainmentService.addLove(activityId);
        return ResultVOUtil.success();
    }

    /*
     * 取消帖子点赞
     */
    @GetMapping("/decreaseLove")
    @Operation(summary = "取消点赞")
    public Result decreaseLove(String activityId){
        entertainmentService.decreaseLove(activityId);
        return ResultVOUtil.success();
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
