package com.campuswindow.activity.mate.service;

import com.campuswindow.activity.activityimage.service.ActivityImageService;
import com.campuswindow.activity.mate.dto.MateActivityDto;
import com.campuswindow.activity.mate.entity.MateActivity;
import com.campuswindow.activity.mate.repository.MateRepository;
import com.campuswindow.activity.mate.vo.MateActivityVo;
import com.campuswindow.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class MateService {

    private MateRepository repository;
    private UserRepository userRepository;
    private ActivityImageService activityImageService;
    public List<MateActivityVo> findAll() {
        return repository.findAllOderByDate()
                .stream().peek(e -> e.setActivityImages(activityImageService.findActivityImageByActivityId(e.getActivityId())))
                .collect(Collectors.toList());
    }
    public MateActivity sendActivity(MateActivityDto mateActivityDto) throws ParseException {
        String activityId = UUID.randomUUID().toString().replaceAll("-", "");
        Timestamp sendTime = new Timestamp(System.currentTimeMillis());
        MateActivity mateActivity = new MateActivity(activityId, mateActivityDto.getActivityTitle(), mateActivityDto.getActivityContent(), sendTime, mateActivityDto.getUserId(), 0);
        MateActivity save = repository.save(mateActivity);
        activityImageService.save(mateActivityDto.getImages(), activityId, mateActivityDto.getUserId(), 0);
        activityImageService.save(mateActivityDto.getVideos(), activityId, mateActivityDto.getUserId(), 1);
        return save;
    }

    public List<MateActivityVo> selectActivity(String userId) {
        return repository.findActivityByUserId(userId)
                .stream().peek(e -> e.setActivityImages(activityImageService.findActivityImageByActivityId(e.getActivityId())))
                .collect(Collectors.toList());
    }

    public void deleteActivity(String activityId) {
        repository.deleteById(activityId);
        activityImageService.deleteActivityImageByActivityId(activityId);
    }

    public void addLove(String activityId) {
        repository.updateLove(activityId, 1);
    }

    public void decreaseLove(String activityId) {
        repository.updateLove(activityId, -1);
    }

    @Autowired
    public void setRepository(MateRepository repository) {
        this.repository = repository;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setActivityImageService(ActivityImageService activityImageService) {
        this.activityImageService = activityImageService;
    }
}
