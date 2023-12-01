package com.campuswindow.activity.entertainment.service;

import com.campuswindow.activity.entertainment.dto.EntertainmentActivityDto;
import com.campuswindow.activity.entertainment.entity.EntertainmentActivity;
import com.campuswindow.activity.entertainment.repository.EntertainmentRepository;
import com.campuswindow.user.entity.User;
import com.campuswindow.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@Service
public class EntertainmentService {

    private EntertainmentRepository repository;
    @Autowired
    private UserRepository userRepository;
    public List<EntertainmentActivity> findAll() {
        Sort.Order order = Sort.Order.desc("date");
        Sort sort = Sort.by(order);
        return repository.findAll(sort);
    }
    public EntertainmentActivity sendActivity(EntertainmentActivityDto entertainmentActivityDto) throws ParseException {
        EntertainmentActivity entertainmentActivity = new EntertainmentActivity();
        entertainmentActivity.setActivityId(UUID.randomUUID().toString().replaceAll("-", ""));
        entertainmentActivity.setDate(new Timestamp(System.currentTimeMillis()));
        entertainmentActivity.setActivityTitle(entertainmentActivityDto.getActivityTitle());
        entertainmentActivity.setActivityContent(entertainmentActivityDto.getActivityContent());
        entertainmentActivity.setUserId(entertainmentActivityDto.getUserId());
        User user = userRepository.findUserNameAndAvatarAndSchoolByUserId(entertainmentActivity.getUserId());
        entertainmentActivity.setUserName(user.getUserName());
        entertainmentActivity.setAvatar(user.getAvatar());
        entertainmentActivity.setSchool(user.getSchool());
        EntertainmentActivity save = repository.save(entertainmentActivity);
        return save;
    }

    public List<EntertainmentActivity> selectActivity(String userId) {
        Sort.Order order = Sort.Order.desc("date");
        Sort sort = Sort.by(order);
        List<EntertainmentActivity> activities =  repository.findActivityByUserId(userId, sort);
        return activities;
    }

    public void deleteActivity(String activityId) {
        repository.deleteById(activityId);
    }

    @Autowired
    public void setRepository(EntertainmentRepository repository) {
        this.repository = repository;
    }
}
