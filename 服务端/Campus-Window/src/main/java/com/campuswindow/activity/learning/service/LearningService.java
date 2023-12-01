package com.campuswindow.activity.learning.service;

import com.campuswindow.activity.learning.dto.LearningActivityDto;
import com.campuswindow.activity.learning.entity.LearningActivity;
import com.campuswindow.activity.learning.repository.LearningRepository;
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
public class LearningService {

    private LearningRepository repository;
    @Autowired
    private UserRepository userRepository;
    public List<LearningActivity> findAll() {
        Sort.Order order = Sort.Order.desc("date");
        Sort sort = Sort.by(order);
        return repository.findAll(sort);
    }
    public LearningActivity sendActivity(LearningActivityDto learningActivityDto) throws ParseException {
        LearningActivity learningActivity = new LearningActivity();
        learningActivity.setActivityId(UUID.randomUUID().toString().replaceAll("-", ""));
        learningActivity.setDate(new Timestamp(System.currentTimeMillis()));
        learningActivity.setActivityTitle(learningActivityDto.getActivityTitle());
        learningActivity.setActivityContent(learningActivityDto.getActivityContent());
        learningActivity.setUserId(learningActivityDto.getUserId());
        User user = userRepository.findUserNameAndAvatarAndSchoolByUserId(learningActivity.getUserId());
        learningActivity.setUserName(user.getUserName());
        learningActivity.setAvatar(user.getAvatar());
        learningActivity.setSchool(user.getSchool());
        LearningActivity save = repository.save(learningActivity);
        return save;
    }

    public List<LearningActivity> selectActivity(String userId) {
        Sort.Order order = Sort.Order.desc("date");
        Sort sort = Sort.by(order);
        List<LearningActivity> activities =  repository.findActivityByUserId(userId, sort);
        return activities;
    }

    public void deleteActivity(String activityId) {
        repository.deleteById(activityId);
    }

    @Autowired
    public void setRepository(LearningRepository repository) {
        this.repository = repository;
    }
}
