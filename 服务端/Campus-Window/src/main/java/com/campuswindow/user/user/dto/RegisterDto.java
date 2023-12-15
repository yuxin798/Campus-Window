package com.campuswindow.user.user.dto;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class RegisterDto {
    @NotNull(message = "用户名不能为空")
    private String userName;
    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;
    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "学校不能为空")
    private String school;
    @NotNull(message = "验证码不能为空")
    private String emailCode;
    @NotNull(message = "验证码Key不能为空")
    private String emailCodeKey;
}
