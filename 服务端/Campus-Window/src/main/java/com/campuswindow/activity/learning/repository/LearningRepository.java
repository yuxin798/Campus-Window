package com.campuswindow.activity.learning.repository;

import com.campuswindow.activity.activity.entity.ActivityVo;
import com.campuswindow.activity.learning.entity.LearningActivity;
import com.campuswindow.activity.learning.vo.LearningActivityVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LearningRepository extends JpaRepository<LearningActivity, String> {

    @Query(value = "select new com.campuswindow.activity.learning.vo.LearningActivityVo(l.activityId, l.activityTitle, l.activityContent, l.date, l.userId, u.userName, u.avatar, u.school, l.love) from LearningActivity as l join User as u on l.userId = u.userId where l.userId = ?1 order by l.date desc ")
    List<LearningActivityVo> findActivityByUserId(String userId);

    @Query(value = "update tbl_activity_learning set love = love + ?2 where activity_id = ?1", nativeQuery = true)
    @Modifying
    void updateLove(String activityId, int flag);

    @Query(value = "select new com.campuswindow.activity.learning.vo.LearningActivityVo(l.activityId, l.activityTitle, l.activityContent, l.date, l.userId, u.userName, u.avatar, u.school, l.love) from LearningActivity as l join User as u on l.userId = u.userId order by l.date desc ")
    List<LearningActivityVo> findAllOderByDate();

    @Query(value = "select new com.campuswindow.activity.activity.entity.ActivityVo(l.activityId, l.activityTitle) " +
            "from LearningActivity as l " +
            "where l.activityTitle like %?1% ")
    List<ActivityVo> findAllLikeActivityTitle(String activityTitle);

    @Query(value = "select new com.campuswindow.activity.learning.vo.LearningActivityVo(l.activityId, l.activityTitle, l.activityContent, l.date, l.userId, u.userName, u.avatar, u.school, l.love) from LearningActivity as l join User as u on l.userId = u.userId where l.activityId = ?1")
    LearningActivityVo findOneByActivityId(String activityId);
}
