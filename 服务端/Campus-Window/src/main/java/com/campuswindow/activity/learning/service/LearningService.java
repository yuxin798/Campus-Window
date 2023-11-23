package com.campuswindow.activity.learning.service;

import com.campuswindow.activity.learning.entity.LearningActivity;
import com.campuswindow.activity.learning.repository.LearningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@Service
public class LearningService {
    @Autowired
    private LearningRepository repository;
    public List<LearningActivity> findAll() {
        Sort.Order order = Sort.Order.desc("date");
        Sort sort = Sort.by(order);
        return repository.findAll(sort);
    }
    public LearningActivity sendActivity(LearningActivity learningActivity) throws ParseException {
        learningActivity.setActivityId(UUID.randomUUID().toString());
        learningActivity.setDate(new Timestamp(System.currentTimeMillis()));
        LearningActivity save = repository.save(learningActivity);
        return save;
    }

    public List<LearningActivity> selectActivity(String userId) {
        Sort.Order order = Sort.Order.desc("date");
        Sort sort = Sort.by(order);
        List<LearningActivity> activities =  repository.findActivityByUserId(userId, sort);
        return activities;
    }
}
