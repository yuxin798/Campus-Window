package com.campuswindow.activity.activity.service;

import com.campuswindow.activity.activity.dto.ActivityDto;
import com.campuswindow.activity.activity.entity.Activity;
import com.campuswindow.activity.activity.repository.ActivityRepository;
import com.campuswindow.activity.activity.vo.ActivityQueryVo;
import com.campuswindow.activity.activity.vo.ActivityVo;
import com.campuswindow.activity.activitycollect.entity.ActivityCollect;
import com.campuswindow.activity.activitycollect.service.ActivityCollectService;
import com.campuswindow.activity.activityimage.entity.ActivityImage;
import com.campuswindow.activity.activityimage.service.ActivityImageService;
import com.campuswindow.activity.activitylove.entity.ActivityLove;
import com.campuswindow.activity.activitylove.service.ActivityLoveService;
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
public class ActivityService {

    private ActivityRepository activityRepository;
    private UserRepository userRepository;
    private ActivityImageService activityImageService;
    private ActivityLoveService activityLoveService;
    private ActivityCollectService activityCollectService;

    public Activity sendActivity(ActivityDto activityDto) throws ParseException {
        String activityId = UUID.randomUUID().toString().replaceAll("-", "");
        Timestamp sendTime = new Timestamp(System.currentTimeMillis());
        Activity activity = new Activity(activityId, activityDto.getActivityTitle(), activityDto.getActivityContent(), sendTime, activityDto.getUserId(), 0, 0, 0, activityDto.getType());
        Activity save = activityRepository.save(activity);
        activityImageService.save(activityDto.getImages(), activityId, activityDto.getUserId(), 0);
        activityImageService.save(activityDto.getVideos(), activityId, activityDto.getUserId(), 1);
        return save;
    }

    public List<ActivityVo> selectActivity(String userId) {
        return activityRepository.findActivityByUserId(userId)
                .stream().peek(e -> e.setActivityImages(activityImageService.findActivityImageByActivityId(e.getActivityId()))).collect(Collectors.toList());
    }

    public void deleteActivity(String activityId) {
        activityRepository.deleteById(activityId);
        activityImageService.deleteActivityImageByActivityId(activityId);
    }

    public List<ActivityQueryVo> findAllLikeActivityTitle(String activityTitle){
        return activityRepository.findAllLikeActivityTitle(activityTitle);
    }

    public ActivityVo findOneByActivityId(String activityId) {
        ActivityVo activityVo = activityRepository.findOneByActivityId(activityId);
        List<ActivityImage> images = activityImageService.findActivityImageByActivityId(activityId);
        activityVo.setActivityImages(images);
        return activityVo;
    }

    public void addLove(ActivityLove activityLove) {
        activityRepository.updateLove(activityLove.getActivityId(), 1);
        activityLoveService.addLove(activityLove);
    }

    public void decreaseLove(ActivityLove activityLove) {
        activityRepository.updateLove(activityLove.getActivityId(), -1);
        activityLoveService.decreaseLove(activityLove);
    }

    public List<ActivityVo> findAllByType(int type) {
        List<ActivityVo> activityVos = activityRepository.findAllByTypeOderByDate(type)
                .stream().peek(e -> e.setActivityImages(activityImageService.findActivityImageByActivityId(e.getActivityId()))).collect(Collectors.toList());
        return activityVos;
    }

    public List<ActivityVo> findAllLoveByUserId(String userId) {
        List<ActivityVo> activityVos = activityRepository.findAllLoveByUserId(userId)
                .stream().peek(e -> e.setActivityImages(activityImageService.findActivityImageByActivityId(e.getActivityId()))).collect(Collectors.toList());
        return activityVos;
    }

    public List<ActivityVo> findAllCollectByUserId(String userId) {
        List<ActivityVo> activityVos = activityRepository.findAllCollectByUserId(userId)
                .stream().peek(e -> e.setActivityImages(activityImageService.findActivityImageByActivityId(e.getActivityId()))).collect(Collectors.toList());
        return activityVos;
    }

    public void addComment(String activityId) {
        activityRepository.updateComment(activityId, 1);
    }

    public void decreaseComment(String activityId) {
        activityRepository.updateComment(activityId, -1);
    }

    public void addCollect(ActivityCollect activityCollect) {
        activityRepository.updateCollect(activityCollect.getActivityId(), 1);
        activityCollectService.addCollect(activityCollect);
    }

    public void decreaseCollect(ActivityCollect activityCollect) {
        activityRepository.updateCollect(activityCollect.getActivityId(), -1);
        activityCollectService.decreaseCollect(activityCollect);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setActivityImageService(ActivityImageService activityImageService) {
        this.activityImageService = activityImageService;
    }
    @Autowired
    public void setActivityLoveService(ActivityLoveService activityLoveService) {
        this.activityLoveService = activityLoveService;
    }
    @Autowired
    public void setActivityRepository(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }
    @Autowired
    public void setActivityCollectService(ActivityCollectService activityCollectService) {
        this.activityCollectService = activityCollectService;
    }
}
