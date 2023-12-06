package com.campuswindow.activity.activityimage.repository;

import com.campuswindow.activity.activityimage.entity.ActivityImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityImageRepository extends JpaRepository<ActivityImage, String> {

    @Query(value = "update tbl_activity_image set activity_id = ?1 where user_id = ?2", nativeQuery = true)
    @Modifying
    void updateActivityIdByUserId(String activityId, String userId);

    @Query(value = "delete from tbl_activity_image where user_id = ?1 and activity_id is null ", nativeQuery = true)
    @Modifying
    void deleteActivityImageByUserId(String userId);
    @Query(value = "delete from tbl_activity_image where activity_id = ?1 ", nativeQuery = true)
    @Modifying
    void deleteActivityImageByActivityId(String activityId);

    @Query(value = "select image from tbl_activity_image where activity_id = ?1",nativeQuery = true)
    List<String> findActivityImageByActivityId(String activityId);
}
