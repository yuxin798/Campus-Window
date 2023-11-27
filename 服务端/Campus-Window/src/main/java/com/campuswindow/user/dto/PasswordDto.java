package com.campuswindow.user.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PasswordDto {
    @NotNull(message = "验证码不能为空")
    private String emailCode;
    @NotNull(message = "验证码Key不能为空")
    private String emailCodeKey;
    @NotNull(message = "用户Id不能为空")
    private String userId;
    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "确认密码不能为空")
    private String confirmPassword;
}
