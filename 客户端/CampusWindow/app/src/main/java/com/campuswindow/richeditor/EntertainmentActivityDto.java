package com.campuswindow.richeditor;

import java.util.List;

public class EntertainmentActivityDto {
    private String activityTitle;
    private String activityContent;
    private String userId;

    private List<String> images;
    private List<String> videos;

    public EntertainmentActivityDto(String activityTitle, String activityContent, String userId, List<String> images, List<String> videos) {
        this.activityTitle = activityTitle;
        this.activityContent = activityContent;
        this.userId = userId;
        this.images = images;
        this.videos = videos;
    }

    public EntertainmentActivityDto() {
    }

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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }
}
