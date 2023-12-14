package com.campuswindow.activity.comment.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReplyCommentDto {
    private String activityId;
    private String userId;
    private String content;
    private String parentId;
    private String toUserId;
    private List<String> images;
    private List<String> videos;
}
