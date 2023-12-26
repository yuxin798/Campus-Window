package com.campuswindow.activity.activity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class EditActivityDto {
    @Schema(description = "活动Id")
    private String activityId;
    @Schema(description = "活动标题")
    private String activityTitle;
    @Schema(description = "活动内容")
    private String activityContent;
    @Schema(description = "所属用户Id")
    private String userId;
    private List<String> images;
    private List<String> videos;
    private int type;
}
