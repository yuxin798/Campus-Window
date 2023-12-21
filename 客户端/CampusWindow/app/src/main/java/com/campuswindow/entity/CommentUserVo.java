package com.campuswindow.entity;


import java.io.Serializable;
import java.util.List;

public class CommentUserVo implements Serializable {
    private String commentId;
    private String activityId;
    private String activityTitle;
    private String content;
    private int love;
    private String sendTime;
    private List<CommentImage> commentImages;

    public CommentUserVo(String commentId, String activityId, String activityTitle, String content, int love, String sendTime) {
        this.commentId = commentId;
        this.activityId = activityId;
        this.activityTitle = activityTitle;
        this.content = content;
        this.love = love;
        this.sendTime = sendTime;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public List<CommentImage> getCommentImages() {
        return commentImages;
    }

    public void setCommentImages(List<CommentImage> commentImages) {
        this.commentImages = commentImages;
    }
}
