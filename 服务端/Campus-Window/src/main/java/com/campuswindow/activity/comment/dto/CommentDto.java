package com.campuswindow.activity.comment.dto;

import lombok.Data;

import java.util.List;
/*
 * @description: 评论DTO实体类
 */
@Data
public class CommentDto {
    private String activityId;
    private String userId;
    private String content;
    private List<String> images;
    private List<String> videos;
}
