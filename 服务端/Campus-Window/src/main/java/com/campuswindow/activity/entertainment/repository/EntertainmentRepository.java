package com.campuswindow.activity.entertainment.repository;

import com.campuswindow.activity.entertainment.entity.EntertainmentActivity;
import com.campuswindow.activity.entertainment.vo.EntertainmentActivityVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntertainmentRepository extends JpaRepository<EntertainmentActivity, String> {

    @Query(value = "select new com.campuswindow.activity.entertainment.vo.EntertainmentActivityVo(e.activityId, e.activityTitle, e.activityContent, e.date, e.userId, u.userName, u.avatar, u.school, e.love) from EntertainmentActivity as e join User as u on e.userId = u.userId where e.userId = ?1 order by e.date desc")
    List<EntertainmentActivityVo> findActivityByUserId(String userId);

    @Query(value = "update tbl_activity_entertainment set love = love + ?2 where activity_id = ?1", nativeQuery = true)
    @Modifying
    void updateLove(String activityId, int flag);

    @Query(value = "select new com.campuswindow.activity.entertainment.vo.EntertainmentActivityVo(e.activityId, e.activityTitle, e.activityContent, e.date, e.userId, u.userName, u.avatar, u.school, e.love) from EntertainmentActivity as e join User as u on e.userId = u.userId order by e.date desc ")
    List<EntertainmentActivityVo> findAllOderByDate();
}
