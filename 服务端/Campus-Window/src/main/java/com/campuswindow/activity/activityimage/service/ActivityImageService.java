package com.campuswindow.activity.activityimage.service;

import com.campuswindow.activity.activityimage.entity.ActivityImage;
import com.campuswindow.activity.activityimage.repository.ActivityImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ActivityImageService {

    private ActivityImageRepository activityImageRepository;

    /*
     * 根据activityId 查询 tbl_activity_image 表中的图片URL
     */
    public List<ActivityImage> findActivityImageByActivityId(String activityId){
        return activityImageRepository.findActivityImageByActivityId(activityId);
    }

    /*
     * 删帖时同时删除 tbl_activity_image 表中的图片数据
     */
    public void deleteActivityImageByActivityId(String activityId){
        activityImageRepository.deleteActivityImageByActivityId(activityId);
    }

    /*
     * 用户发帖 保存图片或视频 type = 0 图片 type = 1 视频
     */
    public void save(List<String> images, String activityId, String userId, int type){
        if (images == null){
            return;
        }
        for(String image : images){
            String imageId = UUID.randomUUID().toString().replace("-", "");
            ActivityImage activityImage = new ActivityImage(imageId, activityId, userId, image, type);
            activityImageRepository.save(activityImage);
        }
    }
    @Autowired
    public void setActivityImageRepository(ActivityImageRepository activityImageRepository) {
        this.activityImageRepository = activityImageRepository;
    }
}

