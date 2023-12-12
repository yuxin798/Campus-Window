package com.campuswindow.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatListDto {
    private String listId;
    //关系表主键
    private String linkId;
    //发送者
    private String fromUserId;
    //接收者
    private String toUserId;
    private String toUserName;
    private String toUserAvatar;
    //发送者是否在窗口
    private int fromWindow;
    //接收者是否在窗口
    private int toWindow;
    private String lastMsg;
    private Date lastMsgTime;
    //未读数 fromUser的未读数
    private int unread;
    //是否被删除
    private int status;
}
