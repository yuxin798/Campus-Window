package com.campuswindow.chat.entity;

import com.campuswindow.chat.entity.Msg;
import lombok.Data;

@Data
public class User {
    private String userId;
    private String userName;
    private String avatar;
    private Msg msg;
}
