package com.campuswindow.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Table(name = "tbl_user")
@Data
public class User {
    @Id
    private String userId;
    @NotNull(message = "用户名不能为空")
    private String userName;
    @NotNull(message = "邮箱不能为空", groups = Login.class)
    @Email(message = "邮箱格式错误")
    private String email;
    @NotNull(message = "密码不能为空", groups = Login.class)
    private String password;
    @NotNull(message = "学校不能为空")
    private String school;
    private String avatar;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;
    private int gender;
    private String signature;

    public @interface Login {}
}
