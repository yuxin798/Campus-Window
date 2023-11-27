package com.campuswindow.activity.mate.service;

import com.campuswindow.activity.mate.entity.MateActivity;
import com.campuswindow.activity.mate.repository.MateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@Service
public class MateService {
    @Autowired
    private MateRepository repository;
    public List<MateActivity> findAll() {
        Sort.Order order = Sort.Order.desc("date");
        Sort sort = Sort.by(order);
        return repository.findAll(sort);
    }
    public MateActivity sendActivity(MateActivity mateActivity) throws ParseException {
        mateActivity.setActivityId(UUID.randomUUID().toString().replaceAll("-", ""));
        mateActivity.setDate(new Timestamp(System.currentTimeMillis()));
        MateActivity save = repository.save(mateActivity);
        return save;
    }

    public List<MateActivity> selectActivity(String userId) {
        Sort.Order order = Sort.Order.desc("date");
        Sort sort = Sort.by(order);
        List<MateActivity> activities =  repository.findActivityByUserId(userId, sort);
        return activities;
    }

    public void deleteActivity(String activityId) {
        repository.deleteById(activityId);
    }
}
