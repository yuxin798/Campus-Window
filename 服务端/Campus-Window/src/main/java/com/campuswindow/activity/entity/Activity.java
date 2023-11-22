package com.campuswindow.activity.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tbl_activity_learning")
@Data
public class Activity {
    @Id
    private String activityId;
    private String activityTitle;
    private String activityContent;
    private String activity_images;
    private Date date;
    private String userId;

}
