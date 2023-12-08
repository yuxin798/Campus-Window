package com.campuswindow.activity.activityimage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * @description: 帖子图片视频实体类
 */
@Data
@Entity
@Table(name = "tbl_activity_image")
@AllArgsConstructor
@NoArgsConstructor
public class ActivityImage {
    @Id
    private String imageId;
    private String activityId;
    private String userId;
    private String image;
    private int type;
}
