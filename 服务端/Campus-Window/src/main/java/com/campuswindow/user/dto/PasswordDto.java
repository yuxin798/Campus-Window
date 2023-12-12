package com.campuswindow.user.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PasswordDto {
    @NotNull(message = "邮箱不能为空")
    private String email;
    @NotNull(message = "密码不能为空")
    private String password;
}
