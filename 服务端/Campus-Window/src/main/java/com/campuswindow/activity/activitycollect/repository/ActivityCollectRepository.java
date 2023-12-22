package com.campuswindow.activity.activitycollect.repository;

import com.campuswindow.activity.activitycollect.entity.ActivityCollect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ActivityCollectRepository extends JpaRepository<ActivityCollect, String> {
    @Query(value = "delete from ActivityCollect where userId =?1 and activityId =?2")
    @Modifying
    void deleteByUserIdAndActivityId(String userId, String activityId);

    @Query(value = "select count(*) from ActivityCollect where userId =?1 and activityId =?2")
    int findByUserIdAndActivityId(String userId, String activityId);
}
