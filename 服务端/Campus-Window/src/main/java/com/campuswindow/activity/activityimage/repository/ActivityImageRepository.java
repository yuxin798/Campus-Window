package com.campuswindow.activity.activityimage.repository;

import com.campuswindow.activity.activityimage.entity.ActivityImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityImageRepository extends JpaRepository<ActivityImage, String> {
    /*
     * 根据活动Id删除活动图片或视频
     */
    @Query(value = "delete from tbl_activity_image where activity_id = ?1 ", nativeQuery = true)
    @Modifying
    void deleteActivityImageByActivityId(String activityId);

    /*
     * 根据活动Id查询活动图片或视频，且排序为视频在前，图片在后
     */
    @Query(value = "select * from tbl_activity_image where activity_id = ?1 order by type desc",nativeQuery = true)
    List<ActivityImage> findActivityImageByActivityId(String activityId);
}
