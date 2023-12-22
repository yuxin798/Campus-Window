package com.campuswindow.chat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatListVo {
    private String listId;
    //关系表主键
    private String linkId;
    //接收者
    private String name;
    private String avatar;
    private int num;
    private String lastMsg;
    private Date lastMsgTime;
    //未读数 fromUser的未读数
    private int unread;
    //是否被删除
    private int status;
    private int type;
}
