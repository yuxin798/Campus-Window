package com.campuswindow.chat.entity;

import lombok.Data;

@Data
public class Msg {
    private int type;//1私发 2群发
    private String content;//发送的内容
    private String toUserId;
}
