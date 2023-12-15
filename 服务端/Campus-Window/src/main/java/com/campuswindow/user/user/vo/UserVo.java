package com.campuswindow.user.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private String userId;
    private String userName;
    private String school;
    private String avatar;
    private int gender;
    private String signature;
    private int loves;
    private int friends;
    private int followers;
    private int fans;
    private String background;
}
