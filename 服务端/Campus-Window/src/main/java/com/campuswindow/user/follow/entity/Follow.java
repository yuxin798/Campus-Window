package com.campuswindow.user.follow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tbl_user_follow")
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
    @Id
    private String followId;
    private String userId;
    private String toUserId;
}
