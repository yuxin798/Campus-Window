package com.campuswindow.chat.entity;

import lombok.Data;

@Data
public class Msg {
    private String msgId;
    private String content;//发送的内容
    private boolean isSend;
    private ChatUserDto chatUserDto;
//    private int type;//1私发 2群发
//    private String toUserId;

}
