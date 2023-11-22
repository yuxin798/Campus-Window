package com.campuswindow.activity.service;

import com.campuswindow.activity.entity.Activity;
import com.campuswindow.activity.repository.ActivityRepository;
import com.campuswindow.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository repository;
    public List<Activity> findAll() {
        Sort.Order order = Sort.Order.desc("date");
        Sort sort = Sort.by(order);
        return repository.findAll(sort);
    }
    public Activity sendActivity(Activity activity){
        activity.setActivityId(UUID.randomUUID().toString());
        activity.setDate(new Date());
        Activity save = repository.save(activity);
        return save;
    }

    public List<Activity> selectActivity(String userId) {
        return repository.findActivityByUserId(userId);
    }
}
