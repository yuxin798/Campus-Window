package com.campuswindow.activity.commentlove.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tbl_comment_love")
@AllArgsConstructor
@NoArgsConstructor
public class CommentLove {
    @Id
    private String loveId;
    private String userId;
    private String commentId;
}
