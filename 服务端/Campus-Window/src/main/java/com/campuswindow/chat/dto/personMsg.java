package com.campuswindow.chat.dto;

public class personMsg {
    private String content;//发送的内容
    private String userId;
    private int type;//1私发 2群发
    private String toUserId;
    private boolean isSend;
}
