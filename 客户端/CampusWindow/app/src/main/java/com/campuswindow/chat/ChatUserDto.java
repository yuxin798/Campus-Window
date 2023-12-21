package com.campuswindow.chat;

import java.io.Serializable;

/*
 * 根据userId查询出来的用户头像和姓名的实体类
 */
public class ChatUserDto implements Serializable {
    private String userId;
    private String userName;
    private String avatar;

    public ChatUserDto() {
    }

    public ChatUserDto(String userId, String userName, String avatar) {
        this.userId = userId;
        this.userName = userName;
        this.avatar = avatar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
