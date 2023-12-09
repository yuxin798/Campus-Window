package com.campuswindow.activity.activity.entity;

import com.campuswindow.activity.activityimage.entity.ActivityImage;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    private String activityId;
    private String activityTitle;
    private String activityContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;
    private String userId;
    private String userName;
    private String avatar;
    private String school;
    private int love;
    private List<ActivityImage> activityImages;

    public Activity(String activityId, String activityTitle, String activityContent, Date date, String avatar, String school, int love, List<ActivityImage> activityImages) {
    }
}
