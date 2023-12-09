package com.campuswindow.activity.mate.service;

import com.campuswindow.activity.activity.entity.ActivityVo;
import com.campuswindow.activity.activityimage.entity.ActivityImage;
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

    private MateRepository mateRepository;
    private UserRepository userRepository;
    private ActivityImageService activityImageService;
    public List<MateActivityVo> findAll() {
        return mateRepository.findAllOderByDate()
                .stream().peek(e -> e.setActivityImages(activityImageService.findActivityImageByActivityId(e.getActivityId())))
                .collect(Collectors.toList());
    }
    public MateActivity sendActivity(MateActivityDto mateActivityDto) throws ParseException {
        String activityId = UUID.randomUUID().toString().replaceAll("-", "");
        Timestamp sendTime = new Timestamp(System.currentTimeMillis());
        MateActivity mateActivity = new MateActivity(activityId, mateActivityDto.getActivityTitle(), mateActivityDto.getActivityContent(), sendTime, mateActivityDto.getUserId(), 0);
        MateActivity save = mateRepository.save(mateActivity);
        activityImageService.save(mateActivityDto.getImages(), activityId, mateActivityDto.getUserId(), 0);
        activityImageService.save(mateActivityDto.getVideos(), activityId, mateActivityDto.getUserId(), 1);
        return save;
    }

    public List<MateActivityVo> selectActivity(String userId) {
        return mateRepository.findActivityByUserId(userId)
                .stream().peek(e -> e.setActivityImages(activityImageService.findActivityImageByActivityId(e.getActivityId())))
                .collect(Collectors.toList());
    }

    public void deleteActivity(String activityId) {
        mateRepository.deleteById(activityId);
        activityImageService.deleteActivityImageByActivityId(activityId);
    }

    public MateActivityVo findOneByActivityId(String activityId) {
        MateActivityVo mateActivityVo = mateRepository.findOneByActivityId(activityId);
        List<ActivityImage> images = activityImageService.findActivityImageByActivityId(activityId);
        mateActivityVo.setActivityImages(images);
        return mateActivityVo;
    }

    public List<ActivityVo> findAllLikeActivityTitle(String activityTitle){
        return mateRepository.findAllLikeActivityTitle(activityTitle);
    }

    public void addLove(String activityId) {
        mateRepository.updateLove(activityId, 1);
    }

    public void decreaseLove(String activityId) {
        mateRepository.updateLove(activityId, -1);
    }

    @Autowired
    public void setRepository(MateRepository mateRepository) {
        this.mateRepository = mateRepository;
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
