package com.campuswindow.activity.mate.service;

import com.campuswindow.activity.mate.dto.MateActivityDto;
import com.campuswindow.activity.mate.entity.MateActivity;
import com.campuswindow.activity.mate.repository.MateRepository;
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
public class MateService {

    private MateRepository repository;
    @Autowired
    private UserRepository userRepository;
    public List<MateActivity> findAll() {
        Sort.Order order = Sort.Order.desc("date");
        Sort sort = Sort.by(order);
        return repository.findAll(sort);
    }
    public MateActivity sendActivity(MateActivityDto mateActivityDto) throws ParseException {
        MateActivity mateActivity = new MateActivity();
        mateActivity.setActivityId(UUID.randomUUID().toString().replaceAll("-", ""));
        mateActivity.setDate(new Timestamp(System.currentTimeMillis()));
        mateActivity.setActivityTitle(mateActivityDto.getActivityTitle());
        mateActivity.setActivityContent(mateActivityDto.getActivityContent());
        mateActivity.setUserId(mateActivityDto.getUserId());
        User user = userRepository.findUserNameAndAvatarAndSchoolByUserId(mateActivity.getUserId());
        mateActivity.setUserName(user.getUserName());
        mateActivity.setAvatar(user.getAvatar());
        mateActivity.setSchool(user.getSchool());
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

    @Autowired
    public void setRepository(MateRepository repository) {
        this.repository = repository;
    }
}
