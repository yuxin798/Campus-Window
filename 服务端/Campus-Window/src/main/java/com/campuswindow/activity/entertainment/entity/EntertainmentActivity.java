package com.campuswindow.activity.entertainment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "tbl_activity_entertainment")
@Data
@Schema(name = "娱乐活动")
public class EntertainmentActivity {
    @Id
    @Schema(name = "活动Id")
    private String activityId;
    @Schema(name = "活动标题")
    private String activityTitle;
    @Schema(name = "活动内容")
    private String activityContent;
    @Schema(name = "活动发布日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp date;
    @Schema(name = "所属用户Id")
    private String userId;
    private String userName;
    private String avatar;
    @Schema(name = "学校")
    private String school;
}
