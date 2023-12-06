package com.campuswindow.activity.comment.dto;

import lombok.Data;

@Data
public class CommentDto {
    private String activityId;
    private String userId;
    private String content;
}
