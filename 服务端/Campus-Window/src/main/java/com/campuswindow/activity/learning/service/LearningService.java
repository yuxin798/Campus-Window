package com.campuswindow.activity.learning.service;

import com.campuswindow.activity.activityimage.service.ActivityImageService;
import com.campuswindow.activity.learning.dto.LearningActivityDto;
import com.campuswindow.activity.learning.entity.LearningActivity;
import com.campuswindow.activity.learning.repository.LearningRepository;
import com.campuswindow.activity.learning.vo.LearningActivityVo;
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
public class LearningService {

    private LearningRepository learningRepository;
    private UserRepository userRepository;
    private ActivityImageService activityImageService;

    /*
     * 发帖
     */
    public LearningActivity sendActivity(LearningActivityDto learningActivityDto) throws ParseException {
        String activityId = UUID.randomUUID().toString().replaceAll("-", "");
        Timestamp sendTime = new Timestamp(System.currentTimeMillis());
        LearningActivity learningActivity = new LearningActivity(activityId, learningActivityDto.getActivityTitle(), learningActivityDto.getActivityContent(), sendTime, learningActivityDto.getUserId(), 0);
        LearningActivity save = learningRepository.save(learningActivity);
        activityImageService.updateActivityIdByUserId(activityId, learningActivityDto.getUserId());
        return save;
    }

    /*
     * 删帖
     */
    public void deleteActivity(String activityId) {
        learningRepository.deleteById(activityId);
    }

    /*
     * 查询所有学术帖子
     */
    public List<LearningActivityVo> findAll() {
        List<LearningActivityVo> learningActivityVos = learningRepository.findAllOderByDate()
                .stream().peek(e -> e.setActivityImages(activityImageService.findActivityImageByActivityId(e.getActivityId()))).collect(Collectors.toList());
        return learningActivityVos;
    }

    /*
     * 根据userId查询某个人的所有帖子
     */
    public List<LearningActivityVo> selectActivity(String userId) {
        return learningRepository.findActivityByUserId(userId)
                .stream().peek(e -> e.setActivityImages(activityImageService.findActivityImageByActivityId(e.getActivityId())))
                .collect(Collectors.toList());
    }

    /*
     * 点赞
     */
    public void addLove(String activityId) {
        learningRepository.updateLove(activityId, 1);
    }
    /*
     * 取消点赞
     */
    public void decreaseLove(String activityId) {
        learningRepository.updateLove(activityId, -1);
    }

    @Autowired
    public void setLearningRepository(LearningRepository learningRepository) {
        this.learningRepository = learningRepository;
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
