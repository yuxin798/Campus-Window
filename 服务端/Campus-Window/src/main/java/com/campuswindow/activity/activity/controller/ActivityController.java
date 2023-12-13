package com.campuswindow.activity.activity.controller;

import com.campuswindow.activity.activitycollect.entity.ActivityCollect;
import com.campuswindow.activity.activitylove.entity.ActivityLove;
import com.campuswindow.activity.activity.dto.ActivityDto;
import com.campuswindow.activity.activity.service.ActivityService;
import com.campuswindow.activity.activity.vo.ActivityVo;
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
@RequestMapping("/activity")
@Tag(name = "活动接口")
public class ActivityController {

    private ActivityService activityService;
    private FileUploadService fileUploadService;

    /*
     * 查询所有娱乐帖子，根据发表时间降序排序
     */
    @GetMapping("/findAllByType")
    @Operation(summary = "根据发帖类型查询所有帖子")
    public Result<List<ActivityVo>> findAllByType(String userId, int type){
        List<ActivityVo> activities = activityService.findAllByType(userId, type);
        return ResultVOUtil.success(activities);
    }

    /*
     * 发帖，同时将图片或视频网络地址保存到数据库中
     */
    @PostMapping("/sendActivity")
    @Operation(summary = "发帖")
    public Result<?> sendActivity(@RequestBody ActivityDto activityDto) throws ParseException {
        activityService.sendActivity(activityDto);
        return ResultVOUtil.success();
    }

    /*
     * 根据帖子id删除帖子
     */
    @GetMapping("/deleteActivity")
    @Operation(summary = "根据帖子id删除帖子")
    public Result<?> deleteActivity(String activityId){
        activityService.deleteActivity(activityId);
        return ResultVOUtil.success();
    }

    /*
     * 根据userId查询某个人的所有帖子
     */
    @GetMapping("/selectActivity")
    @Operation(summary = "根据userId查询某个人的所有帖子")
    public Result<List<ActivityVo>> selectActivity(String userId){
        List<ActivityVo> activities = activityService.selectActivity(userId);
        return ResultVOUtil.success(activities);
    }

    /*
     * 帖子点赞
     */
    @PostMapping("/addLove")
    @Operation(summary = "点赞")
    public Result<?> addLove(@RequestBody ActivityLove activityLove){
        activityService.addLove(activityLove);
        return ResultVOUtil.success();
    }

    /*
     * 取消帖子点赞
     */
    @PostMapping("/decreaseLove")
    @Operation(summary = "取消点赞")
    public Result<?> decreaseLove(@RequestBody ActivityLove activityLove){
        activityService.decreaseLove(activityLove);
        return ResultVOUtil.success();
    }

    /*
     * 收藏帖子
     */
    @PostMapping("/addCollect")
    @Operation(summary = "收藏")
    public Result<?> addCollect(@RequestBody ActivityCollect activityCollect){
        activityService.addCollect(activityCollect);
        return ResultVOUtil.success();
    }

    /*
     * 取消收藏帖子
     */
    @PostMapping("/decreaseCollect")
    @Operation(summary = "取消收藏")
    public Result<?> decreaseCollect(@RequestBody ActivityCollect activityCollect){
        activityService.decreaseCollect(activityCollect);
        return ResultVOUtil.success();
    }

    @GetMapping("/findAllLoveByUserId")
    @Operation(summary = "根据UserId查询所有点赞的帖子")
    public Result<List<ActivityVo>> findAllLoveByUserId(String userId){
        List<ActivityVo> activities = activityService.findAllLoveByUserId(userId);
        return ResultVOUtil.success(activities);
    }

    @GetMapping("/findAllCollectByUserId")
    @Operation(summary = "根据UserId查询所有收藏的帖子")
    public Result<List<ActivityVo>> findAllCollectByUserId(String userId){
        List<ActivityVo> activities = activityService.findAllCollectByUserId(userId);
        return ResultVOUtil.success(activities);
    }

    @GetMapping("/findAllLikeActivityTitle")
    @Operation(summary = "根据帖子标题模糊查询所有帖子")
    public Result<List<ActivityVo>> findAllLikeActivityTitle(String activityTitle) {
        return ResultVOUtil.success(activityService.findAllLikeActivityTitle(activityTitle));
    }

    @GetMapping("/findActivityByActivityId/{userId}/{activityId}")
    @Operation(summary = "根据帖子id查询帖子")
    public Result<ActivityVo> findActivityByActivityId(@PathVariable("userId") String userId, @PathVariable("activityId") String activityId){
        return ResultVOUtil.success(activityService.findOneByActivityId(userId, activityId));
    }

    @Autowired
    public void setEntertainmentService(ActivityService activityService) {
        this.activityService = activityService;
    }
    @Autowired
    public void setFileUploadService(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }
}
