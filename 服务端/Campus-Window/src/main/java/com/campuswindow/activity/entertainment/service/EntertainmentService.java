package com.campuswindow.activity.entertainment.service;

import com.campuswindow.activity.entertainment.entity.EntertainmentActivity;
import com.campuswindow.activity.entertainment.repository.EntertainmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@Service
public class EntertainmentService {
    @Autowired
    private EntertainmentRepository repository;
    public List<EntertainmentActivity> findAll() {
        Sort.Order order = Sort.Order.desc("date");
        Sort sort = Sort.by(order);
        return repository.findAll(sort);
    }
    public EntertainmentActivity sendActivity(EntertainmentActivity entertainmentActivity) throws ParseException {
        entertainmentActivity.setActivityId(UUID.randomUUID().toString());
        entertainmentActivity.setDate(new Timestamp(System.currentTimeMillis()));
        EntertainmentActivity save = repository.save(entertainmentActivity);
        return save;
    }

    public List<EntertainmentActivity> selectActivity(String userId) {
        Sort.Order order = Sort.Order.desc("date");
        Sort sort = Sort.by(order);
        List<EntertainmentActivity> activities =  repository.findActivityByUserId(userId, sort);
        return activities;
    }
}
