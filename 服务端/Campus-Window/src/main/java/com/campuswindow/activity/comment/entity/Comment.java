package com.campuswindow.activity.comment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
/*
 * @description: 评论实体类
 */
@Data
@Entity
@Table(name = "tbl_comment")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    private String commentId;
    private String activityId;
    private String userId;
    private String content;
    private int love;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp sendTime;
}
