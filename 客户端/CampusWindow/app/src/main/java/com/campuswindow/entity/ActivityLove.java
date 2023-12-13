package com.campuswindow.entity;


public class ActivityLove {

    private String loveId;
    private String userId;
    private String activityId;

    public ActivityLove(String userId, String activityId) {
        this.userId = userId;
        this.activityId = activityId;
    }

    public ActivityLove(String loveId, String userId, String activityId) {
        this.loveId = loveId;
        this.userId = userId;
        this.activityId = activityId;
    }

    public ActivityLove() {
    }

    public String getLoveId() {
        return loveId;
    }

    public void setLoveId(String loveId) {
        this.loveId = loveId;
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

    @Override
    public String toString() {
        return "ActivityLove{" +
                "loveId='" + loveId + '\'' +
                ", userId='" + userId + '\'' +
                ", activityId='" + activityId + '\'' +
                '}';
    }
}
