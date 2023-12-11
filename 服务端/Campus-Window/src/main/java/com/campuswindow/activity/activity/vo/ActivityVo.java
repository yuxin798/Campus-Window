package com.campuswindow.activity.activity.vo;

import com.campuswindow.activity.activityimage.entity.ActivityImage;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityVo {
    private String activityId;
    private String activityTitle;
    private String activityContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;
    private String userId;
    private String userName;
    private String avatar;
    private String school;
    private int love;
    private int comment;
    private int collect;
    private List<ActivityImage> activityImages;

    public ActivityVo(String activityId, String activityTitle, String activityContent, Date date, String userId, String userName, String avatar, String school, int love, int comment, int collect) {
        this.activityId = activityId;
        this.activityTitle = activityTitle;
        this.activityContent = activityContent;
        this.date = date;
        this.userId = userId;
        this.userName = userName;
        this.avatar = avatar;
        this.school = school;
        this.love = love;
        this.comment = comment;
        this.collect = collect;
    }
}
