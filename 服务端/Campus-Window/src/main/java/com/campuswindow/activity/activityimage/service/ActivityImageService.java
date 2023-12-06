package com.campuswindow.activity.activityimage.service;

import com.campuswindow.activity.activityimage.entity.ActivityImage;
import com.campuswindow.activity.activityimage.repository.ActivityImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActivityImageService {

    private ActivityImageRepository activityImageRepository;

    @Autowired
    public void setActivityImageRepository(ActivityImageRepository activityImageRepository) {
        this.activityImageRepository = activityImageRepository;
    }

    /*
     * 客户上传图片，保存图片数据
     */
    public void saveActivityImage(ActivityImage activityImage){
        activityImageRepository.save(activityImage);
    }

    /*
     * 用户发帖后，根据UserId 更改 tbl_activity_image 表中的activityId
     */
    public void updateActivityIdByUserId(String activityId, String userId){
        activityImageRepository.updateActivityIdByUserId(activityId, userId);
    }

    /*
     * 用户点击返回按键表明不再发帖，删除 tbl_activity_image 表中的无效的图片数据
     */
    public void deleteActivityImageByUserId(String userId){
        activityImageRepository.deleteActivityImageByUserId(userId);
    }

    public List<String> findActivityImageByActivityId(String activityId){
        return activityImageRepository.findActivityImageByActivityId(activityId);
    }

    /*
     * 删帖时同时删除 tbl_activity_image 表中的图片数据
     */
    public void deleteActivityImageByActivityId(String activityId){
        activityImageRepository.deleteActivityImageByActivityId(activityId);
    }
}
