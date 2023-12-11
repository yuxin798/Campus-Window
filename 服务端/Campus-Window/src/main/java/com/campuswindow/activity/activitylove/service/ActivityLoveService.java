package com.campuswindow.activity.activitylove.service;

import com.campuswindow.activity.activitylove.entity.ActivityLove;
import com.campuswindow.activity.activitylove.repository.ActivityLoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActivityLoveService {

    private ActivityLoveRepository activityLoveRepository;

    public void addLove(ActivityLove activityLove) {
        String activityLoveId = UUID.randomUUID().toString().replace("-", "");
        activityLove.setLoveId(activityLoveId);
        activityLoveRepository.save(activityLove);
    }

    public void decreaseLove(ActivityLove activityLove) {
        activityLoveRepository.deleteByUserIdAndActivityId(activityLove.getUserId(), activityLove.getActivityId());
    }

    @Autowired
    public void setActivityLoveRepository(ActivityLoveRepository activityLoveRepository) {
        this.activityLoveRepository = activityLoveRepository;
    }
}
