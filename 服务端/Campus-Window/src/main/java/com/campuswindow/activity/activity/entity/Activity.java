package com.campuswindow.activity.activity.entity;

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
@Table(name = "tbl_activity")
@Data
@Schema(name = "娱乐活动")
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    @Id
    private String activityId;
    private String activityTitle;
    private String activityContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp date;
    private String userId;
    private int love;
    private int comment;
    private int collect;
    private int type;
}
