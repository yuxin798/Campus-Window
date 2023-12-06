package com.campuswindow.activity.comment.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
//    private List<String> images;

}
