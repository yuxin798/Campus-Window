package com.campuswindow.activity.activitylove.repository;

import com.campuswindow.activity.activitylove.entity.ActivityLove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ActivityLoveRepository extends JpaRepository<ActivityLove, String> {
    @Query(value = "delete from ActivityLove where userId =?1 and activityId =?2")
    @Modifying
    void deleteByUserIdAndActivityId(String userId, String activityId);
}
