package com.campuswindow.activity.activitycollect.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tbl_activity_collect")
public class ActivityCollect {
    @Id
    private String collectId;
    private String userId;
    private String activityId;
}
