package com.campuswindow.activity.activity.service;

import com.campuswindow.activity.activity.dto.ActivityDto;
import com.campuswindow.activity.activity.entity.Activity;
import com.campuswindow.activity.activity.repository.ActivityRepository;
import com.campuswindow.activity.activity.vo.ActivityVo;
import com.campuswindow.activity.activitycollect.entity.ActivityCollect;
import com.campuswindow.activity.activitycollect.service.ActivityCollectService;
import com.campuswindow.activity.activityimage.entity.ActivityImage;
import com.campuswindow.activity.activityimage.service.ActivityImageService;
import com.campuswindow.activity.activitylove.entity.ActivityLove;
import com.campuswindow.activity.activitylove.service.ActivityLoveService;
import com.campuswindow.user.follow.service.FollowService;
import com.campuswindow.user.user.service.UserService;
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
    private ActivityImageService activityImageService;
    private ActivityLoveService activityLoveService;
    private ActivityCollectService activityCollectService;
    private UserService userService;
    private FollowService followService;

    public Activity sendActivity(ActivityDto activityDto) throws ParseException {
        String activityId = UUID.randomUUID().toString().replaceAll("-", "");
        Timestamp sendTime = new Timestamp(System.currentTimeMillis());
        String school = userService.findSchool(activityDto.getUserId());
        Activity activity = new Activity(activityId, activityDto.getActivityTitle(), activityDto.getActivityContent(), sendTime, activityDto.getUserId(), school, 0, 0, 0, activityDto.getType());
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

    public List<ActivityVo> findAllLikeActivityTitle(String activityTitle){
        return activityRepository.findAllLikeActivityTitle(activityTitle);
    }

    public ActivityVo findOneByActivityId(String userId, String activityId, String toUserId) {
        ActivityVo activityVo = activityRepository.findOneByActivityId(activityId);
        List<ActivityImage> images = activityImageService.findActivityImageByActivityId(activityId);
        activityVo.setActivityImages(images);
        boolean loved = activityLoveService.findActivityLoveByUserIdAndActivityId(userId, activityId);
        activityVo.setLoved(loved);
        boolean collected = activityCollectService.findActivityCollectByUserIdAndActivityId(userId, activityId);
        activityVo.setCollected(collected);
        boolean followed = followService.findFollowByUserIdAndToUserId(userId, toUserId);
        activityVo.setFollowed(followed);
        return activityVo;
    }

    public void addLove(ActivityLove activityLove) {
        activityRepository.updateLove(activityLove.getActivityId(), 1);
        activityLoveService.addLove(activityLove);
        String userId = activityRepository.findUserIdByActivityId(activityLove.getActivityId());
        userService.updateLovesByUserId(userId, 1);
    }

    public void decreaseLove(ActivityLove activityLove) {
        activityRepository.updateLove(activityLove.getActivityId(), -1);
        activityLoveService.decreaseLove(activityLove);
        String userId = activityRepository.findUserIdByActivityId(activityLove.getActivityId());
        userService.updateLovesByUserId(userId, -1);
    }

    public List<ActivityVo> findAllByType(String userId, int type) {
        String school = userService.findSchool(userId);
        List<ActivityVo> activityVos = activityRepository.findAllByTypeOderByDate(school, type)
                .stream()
                .peek(e-> e.setLoved(activityLoveService.findActivityLoveByUserIdAndActivityId(userId, e.getActivityId())))
                .peek(e -> e.setCollected(activityCollectService.findActivityCollectByUserIdAndActivityId(userId, e.getActivityId())))
                .peek(e -> e.setActivityImages(activityImageService.findActivityImageByActivityId(e.getActivityId()))).collect(Collectors.toList());
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
    public ActivityService(ActivityRepository activityRepository, ActivityImageService activityImageService, ActivityLoveService activityLoveService, ActivityCollectService activityCollectService, UserService userService, FollowService followService) {
        this.activityRepository = activityRepository;
        this.activityImageService = activityImageService;
        this.activityLoveService = activityLoveService;
        this.activityCollectService = activityCollectService;
        this.userService = userService;
        this.followService = followService;
    }
}
