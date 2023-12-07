package com.campuswindow.activity.entertainment.service;

import com.campuswindow.activity.activityimage.service.ActivityImageService;
import com.campuswindow.activity.entertainment.dto.EntertainmentActivityDto;
import com.campuswindow.activity.entertainment.entity.EntertainmentActivity;
import com.campuswindow.activity.entertainment.repository.EntertainmentRepository;
import com.campuswindow.activity.entertainment.vo.EntertainmentActivityVo;
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
public class EntertainmentService {

    private EntertainmentRepository entertainmentRepository;
    private UserRepository userRepository;
    private ActivityImageService activityImageService;

    public List<EntertainmentActivityVo> findAll() {
        List<EntertainmentActivityVo> entertainmentActivityVos = entertainmentRepository.findAllOderByDate()
                .stream().peek(e -> e.setActivityImages(activityImageService.findActivityImageByActivityId(e.getActivityId()))).collect(Collectors.toList());
        return entertainmentActivityVos;
    }
    public EntertainmentActivity sendActivity(EntertainmentActivityDto entertainmentActivityDto) throws ParseException {
        String activityId = UUID.randomUUID().toString().replaceAll("-", "");
        Timestamp sendTime = new Timestamp(System.currentTimeMillis());
        activityImageService.updateActivityIdByUserId(activityId, entertainmentActivityDto.getUserId());
        EntertainmentActivity entertainmentActivity = new EntertainmentActivity(activityId, entertainmentActivityDto.getActivityTitle(), entertainmentActivityDto.getActivityContent(), sendTime, entertainmentActivityDto.getUserId(), 0);
        EntertainmentActivity save = entertainmentRepository.save(entertainmentActivity);
        return save;
    }

    public List<EntertainmentActivityVo> selectActivity(String userId) {
        return entertainmentRepository.findActivityByUserId(userId)
                .stream().peek(e -> e.setActivityImages(activityImageService.findActivityImageByActivityId(e.getActivityId()))).collect(Collectors.toList());
    }

    public void deleteActivity(String activityId) {
        entertainmentRepository.deleteById(activityId);
        activityImageService.deleteActivityImageByActivityId(activityId);
    }
    public void addLove(String activityId) {
        entertainmentRepository.updateLove(activityId, 1);
    }

    public void decreaseLove(String activityId) {
        entertainmentRepository.updateLove(activityId, -1);
    }
    @Autowired
    public void setEntertainmentRepository(EntertainmentRepository entertainmentRepository) {
        this.entertainmentRepository = entertainmentRepository;
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
