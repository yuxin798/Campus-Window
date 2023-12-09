package com.campuswindow.activity.activity.service;

import com.campuswindow.activity.activity.entity.Activity;
import com.campuswindow.activity.activity.entity.ActivityVo;
import com.campuswindow.activity.entertainment.service.EntertainmentService;
import com.campuswindow.activity.entertainment.vo.EntertainmentActivityVo;
import com.campuswindow.activity.learning.service.LearningService;
import com.campuswindow.activity.learning.vo.LearningActivityVo;
import com.campuswindow.activity.mate.service.MateService;
import com.campuswindow.activity.mate.vo.MateActivityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {
    private final EntertainmentService entertainmentService;
    private final LearningService learningService;
    private final MateService mateService;
    public List<ActivityVo> findAllLikeActivityTitle(String activityTitle) {
        List<ActivityVo> activityVos = new ArrayList<>();
        activityVos.addAll(entertainmentService.findAllLikeActivityTitle(activityTitle));
        activityVos.addAll(learningService.findAllLikeActivityTitle(activityTitle));
        activityVos.addAll(mateService.findAllLikeActivityTitle(activityTitle));
        return activityVos;
    }

    public Activity findActivityByActivityId(String activityId) {
        EntertainmentActivityVo oneByActivityId = entertainmentService.findOneByActivityId(activityId);
        LearningActivityVo oneByActivityId1 = learningService.findOneByActivityId(activityId);
        MateActivityVo oneByActivityId2 = mateService.findOneByActivityId(activityId);
        if (oneByActivityId != null){
            return new Activity(oneByActivityId.getActivityId(), oneByActivityId.getActivityTitle(), oneByActivityId.getActivityContent(), oneByActivityId.getDate(), oneByActivityId.getAvatar(), oneByActivityId.getSchool(), oneByActivityId.getLove(), oneByActivityId.getActivityImages());
        }else if (oneByActivityId1 != null){
            return new Activity(oneByActivityId.getActivityId(), oneByActivityId.getActivityTitle(), oneByActivityId.getActivityContent(), oneByActivityId.getDate(), oneByActivityId.getAvatar(), oneByActivityId.getSchool(), oneByActivityId.getLove(), oneByActivityId.getActivityImages());
        }else {
            return new Activity(oneByActivityId2.getActivityId(), oneByActivityId2.getActivityTitle(), oneByActivityId2.getActivityContent(), oneByActivityId2.getDate(), oneByActivityId2.getAvatar(), oneByActivityId2.getSchool(), oneByActivityId2.getLove(), oneByActivityId2.getActivityImages());
        }
    }

    @Autowired
    public ActivityService(EntertainmentService entertainmentService, MateService mateService, LearningService learningService) {
        this.entertainmentService = entertainmentService;
        this.mateService = mateService;
        this.learningService = learningService;
    }
}
