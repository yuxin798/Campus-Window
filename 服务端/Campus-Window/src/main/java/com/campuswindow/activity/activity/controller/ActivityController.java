package com.campuswindow.activity.activity.controller;

import com.campuswindow.activity.activity.entity.Activity;
import com.campuswindow.activity.activity.entity.ActivityVo;
import com.campuswindow.activity.activity.service.ActivityService;
import com.campuswindow.utils.ResultVOUtil;
import com.campuswindow.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    private ActivityService activityService;

    @GetMapping("/findAllLikeActivityTitle/{ActivityTitle}")
    public Result<List<ActivityVo>> findAllLikeActivityTitle(@PathVariable String ActivityTitle){
        return ResultVOUtil.success(activityService.findAllLikeActivityTitle(ActivityTitle));
    }

    @GetMapping("/findActivityByActivityId/{activityId}")
    public Result<Activity> findActivityByActivityId(@PathVariable String activityId){
        return ResultVOUtil.success(activityService.findActivityByActivityId(activityId));
    }

    @Autowired
    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }
}
