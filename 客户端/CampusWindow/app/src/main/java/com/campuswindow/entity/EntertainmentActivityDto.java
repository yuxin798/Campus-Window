package com.campuswindow.entity;

import java.io.Serializable;

public class EntertainmentActivityDto implements Serializable {
    private String activityTitle;//活动标题
    private String activityContent;//活动内容
    private String userId;//所属用户

    public EntertainmentActivityDto(){}

    public EntertainmentActivityDto(String activityTitle, String activityContent, String userId) {
        this.activityTitle = activityTitle;
        this.activityContent = activityContent;
        this.userId = userId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
