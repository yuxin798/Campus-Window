package com.campuswindow.activity.comment.vo;

import com.campuswindow.activity.commentimage.entity.CommentImage;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentUserVo {
    private String commentId;
    private String activityId;
    private String activityTitle;
    private String content;
    private int love;
    //不能为时间戳格式 数据库查询出来的格式是Date
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sendTime;
    private List<CommentImage> commentImages;

    public CommentUserVo(String commentId, String activityId, String activityTitle, String content, int love, Date sendTime) {
        this.commentId = commentId;
        this.activityId = activityId;
        this.activityTitle = activityTitle;
        this.content = content;
        this.love = love;
        this.sendTime = sendTime;
    }
}
