package com.campuswindow.entity;

public class ActivityCollect {
    private String collectId;
    private String userId;
    private String activityId;

    public ActivityCollect() {
    }

    public ActivityCollect(String userId, String activityId) {
        this.userId = userId;
        this.activityId = activityId;
    }

    public ActivityCollect(String collectId, String userId, String activityId) {
        this.collectId = collectId;
        this.userId = userId;
        this.activityId = activityId;
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
}
