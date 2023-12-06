package com.campuswindow.activity.learning.repository;

import com.campuswindow.activity.learning.entity.LearningActivity;
import com.campuswindow.activity.learning.vo.LearningActivityVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LearningRepository extends JpaRepository<LearningActivity, String> {

    @Query(value = "select new com.campuswindow.activity.learning.vo.LearningActivityVo(activityId, activityTitle, activityContent, date, userId, userName, avatar, school, love) from LearningActivity order by date desc ")
    List<LearningActivityVo> findActivityByUserId(String userId);

    @Query(value = "update tbl_activity_learning set love = love + ?2 where activity_id = ?1", nativeQuery = true)
    @Modifying
    void updateLove(String activityId, int flag);

    @Query(value = "select new com.campuswindow.activity.learning.vo.LearningActivityVo(activityId, activityTitle, activityContent, date, userId, userName, avatar, school, love) from LearningActivity order by date desc ")
    List<LearningActivityVo> findAllOderByDate();
}
