package com.campuswindow.activity.activity.repository;

import com.campuswindow.activity.activity.entity.Activity;
import com.campuswindow.activity.activity.vo.ActivityQueryVo;
import com.campuswindow.activity.activity.vo.ActivityVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, String> {

    @Query(value = "select new com.campuswindow.activity.activity.vo.ActivityVo(e.activityId, e.activityTitle, e.activityContent, e.date, e.userId, u.userName, u.avatar, u.school, e.love, e.comment, e.collect) from Activity as e join User as u on e.userId = u.userId where e.userId = ?1 order by e.date desc")
    List<ActivityVo> findActivityByUserId(String userId);

    @Query(value = "update Activity set love = love + ?2 where activityId = ?1")
    @Modifying
    void updateLove(String activityId, int flag);

    @Query(value = "select new com.campuswindow.activity.activity.vo.ActivityVo(e.activityId, e.activityTitle, e.activityContent, e.date, e.userId, u.userName, u.avatar, u.school, e.love, e.comment, e.collect) from Activity as e join User as u on e.userId = u.userId order by e.date desc ")
    List<ActivityVo> findAllOderByDate();

    @Query(value = "select new com.campuswindow.activity.activity.vo.ActivityQueryVo(e.activityId, e.activityTitle) " +
            "from Activity as e " +
            "where e.activityTitle like %?1%")
    List<ActivityQueryVo> findAllLikeActivityTitle(String activityTitle);

    @Query(value = "select new com.campuswindow.activity.activity.vo.ActivityVo(e.activityId, e.activityTitle, e.activityContent, e.date, e.userId, u.userName, u.avatar, u.school, e.love, e.comment, e.collect) from Activity as e join User as u on e.userId = u.userId where e.activityId = ?1")
    ActivityVo findOneByActivityId(String activityId);

    @Query(value = "select new com.campuswindow.activity.activity.vo.ActivityVo(e.activityId, e.activityTitle, e.activityContent, e.date, e.userId, u.userName, u.avatar, u.school, e.love, e.comment, e.collect) from Activity as e join User as u on e.userId = u.userId where e.type = ?1 order by e.date desc ")
    List<ActivityVo> findAllByTypeOderByDate(int type);

    @Query(value = "update Activity set collect = collect + ?2 where activityId = ?1")
    @Modifying
    void updateCollect(String activityId, int i);

    @Query(value = "update Activity set comment = comment + ?2 where activityId = ?1")
    @Modifying
    void updateComment(String activityId, int i);

    @Query(value = "select new com.campuswindow.activity.activity.vo.ActivityVo(e.activityId, e.activityTitle, e.activityContent, e.date, e.userId, u.userName, u.avatar, u.school, e.love, e.comment, e.collect) from Activity as e join ActivityLove l on e.activityId = l.activityId join User as u on e.userId = u.userId where l.userId = ?1")
    List<ActivityVo> findAllLoveByUserId(String userId);

    @Query(value = "select new com.campuswindow.activity.activity.vo.ActivityVo(e.activityId, e.activityTitle, e.activityContent, e.date, e.userId, u.userName, u.avatar, u.school, e.love, e.comment, e.collect) from Activity as e join ActivityCollect l on e.activityId = l.activityId join User as u on e.userId = u.userId where l.userId = ?1")
    List<ActivityVo> findAllCollectByUserId(String userId);
}
