package com.campuswindow.activity.comment.vo;

import com.campuswindow.activity.commentimage.entity.CommentImage;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/*
 * @description:评论返回前端实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    private String commentId;
    private String activityId;
    private String userId;
    private String content;
    private int love;
    //不能为时间戳格式 数据库查询出来的格式是Date
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sendTime;
    private String userName;
    private String avatar;
    private boolean isLoved;
    private List<CommentImage> commentImages;

    public CommentVo(String commentId, String activityId, String userId, String content, int love, Date sendTime, String userName, String avatar) {
        this.commentId = commentId;
        this.activityId = activityId;
        this.userId = userId;
        this.content = content;
        this.love = love;
        this.sendTime = sendTime;
        this.userName = userName;
        this.avatar = avatar;
    }
}
