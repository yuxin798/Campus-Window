package com.campuswindow.activity.learning.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class LearningActivityDto {
    @Schema(name = "活动标题")
    private String activityTitle;
    @Schema(name = "活动内容")
    private String activityContent;
    @Schema(name = "所属用户Id")
    private String userId;
    private List<String> images;
    private List<String> videos;
}
