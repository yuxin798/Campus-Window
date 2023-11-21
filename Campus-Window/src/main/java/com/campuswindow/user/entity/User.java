package com.practicaltraining.user.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_user")
@Data
public class User {
    @Id
    private String userId;
    @NotNull(message = "帐号不能为空")
    private String userName;
    @NotNull(message = "邮箱不能为空")
    private String email;
    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "学校不能为空")
    private String school;
    private String avatar;
}
