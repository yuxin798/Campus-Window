package com.campuswindow.activity.entertainment.repository;

import com.campuswindow.activity.entertainment.entity.EntertainmentActivity;
import com.campuswindow.activity.entertainment.vo.EntertainmentActivityVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntertainmentRepository extends JpaRepository<EntertainmentActivity, String> {

    @Query(value = "select new com.campuswindow.activity.entertainment.vo.EntertainmentActivityVo(activityId, activityTitle, activityContent, date, userId, userName, avatar, school, love) from EntertainmentActivity where userId = ?1 order by date desc")
    List<EntertainmentActivityVo> findActivityByUserId(String userId);

    @Query(value = "update tbl_activity_entertainment set love = love + ?2 where activity_id = ?1", nativeQuery = true)
    @Modifying
    void updateLove(String activityId, int flag);

    @Query(value = "select new com.campuswindow.activity.entertainment.vo.EntertainmentActivityVo(activityId, activityTitle, activityContent, date, userId, userName, avatar, school, love) from EntertainmentActivity order by date desc ")
    List<EntertainmentActivityVo> findAllOderByDate();
}
