package com.campuswindow.activity.mate.repository;

import com.campuswindow.activity.activity.entity.ActivityVo;
import com.campuswindow.activity.mate.entity.MateActivity;
import com.campuswindow.activity.mate.vo.MateActivityVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MateRepository extends JpaRepository<MateActivity, String> {

    @Query(value = "select new com.campuswindow.activity.mate.vo.MateActivityVo(m.activityId, m.activityTitle, m.activityContent, m.date, m.userId, u.userName, u.avatar, u.school, m.love) from MateActivity as m join User as u on m.userId = u.userId where m.userId = ?1 order by m.date desc")
    List<MateActivityVo> findActivityByUserId(String userId);

    @Query(value = "update tbl_activity_mate set love = love + ?2 where activity_id = ?1", nativeQuery = true)
    @Modifying
    void updateLove(String activityId, int i);

    @Query(value = "select new com.campuswindow.activity.mate.vo.MateActivityVo(m.activityId, m.activityTitle, m.activityContent, m.date, m.userId, u.userName, u.avatar, u.school, m.love) from MateActivity as m join User as u on m.userId = u.userId order by m.date desc ")
    List<MateActivityVo> findAllOderByDate();

    @Query(value = "select new com.campuswindow.activity.activity.entity.ActivityVo(m.activityId, m.activityTitle) " +
            "from MateActivity as m " +
            "where m.activityTitle like %?1% ")
    List<ActivityVo> findAllLikeActivityTitle(String activityTitle);

    @Query(value = "select new com.campuswindow.activity.mate.vo.MateActivityVo(m.activityId, m.activityTitle, m.activityContent, m.date, m.userId, u.userName, u.avatar, u.school, m.love) from MateActivity as m join User as u on m.userId = u.userId where m.activityId = ?1")
    MateActivityVo findOneByActivityId(String activityId);
}
