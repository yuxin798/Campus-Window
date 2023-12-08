package com.campuswindow.activity.commentimage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * @description: 评论图片视频实体类
 */
@Data
@Entity
@Table(name = "tbl_comment_image")
@AllArgsConstructor
@NoArgsConstructor
public class CommentImage {
    @Id
    private String imageId;
    private String commentId;
    private String userId;
    private String image;
    private int type;
}
