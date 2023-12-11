package com.campuswindow.activity.activitycollect.service;

import com.campuswindow.activity.activitycollect.entity.ActivityCollect;
import com.campuswindow.activity.activitycollect.repository.ActivityCollectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActivityCollectService {
    private ActivityCollectRepository activityCollectRepository;

    public void addCollect(ActivityCollect activityCollect) {
        String activityCollectId = UUID.randomUUID().toString().replace("-", "");
        activityCollect.setCollectId(activityCollectId);
        activityCollectRepository.save(activityCollect);
    }

    public void decreaseCollect(ActivityCollect activityCollect) {
        activityCollectRepository.deleteByUserIdAndActivityId(activityCollect.getUserId(), activityCollect.getActivityId());
    }

    @Autowired

    public void setActivityCollectRepository(ActivityCollectRepository activityCollectRepository) {
        this.activityCollectRepository = activityCollectRepository;
    }
}
