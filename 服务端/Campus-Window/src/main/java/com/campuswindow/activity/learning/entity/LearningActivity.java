package com.campuswindow.activity.learning.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "tbl_activity_learning")
@Data
@Schema(name = "学术活动")
@AllArgsConstructor
@NoArgsConstructor
public class LearningActivity {
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
    @Schema(name = "点赞数")
    private int love;
}
