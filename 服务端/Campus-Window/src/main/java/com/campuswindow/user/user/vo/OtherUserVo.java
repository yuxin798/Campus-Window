package com.campuswindow.user.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtherUserVo {
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
    private boolean followed;
    private String background;

    public OtherUserVo(String userId, String userName, String school, String avatar, int gender, String signature, int loves, int friends, int followers, int fans, String background) {
        this.userId = userId;
        this.userName = userName;
        this.school = school;
        this.avatar = avatar;
        this.gender = gender;
        this.signature = signature;
        this.loves = loves;
        this.friends = friends;
        this.followers = followers;
        this.fans = fans;
        this.background = background;
    }
}
