package com.campuswindow.activity.activitylove.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tbl_activity_love")
public class ActivityLove {
    @Id
    private String loveId;
    private String userId;
    private String activityId;
}
