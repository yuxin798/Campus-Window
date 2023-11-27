package com.campuswindow.activity.entertainment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SendActivityDto {
    @Schema(name = "活动标题")
    private String activityTitle;
    @Schema(name = "活动内容")
    private String activityContent;
    @Schema(name = "所属用户Id")
    private String userId;
}
