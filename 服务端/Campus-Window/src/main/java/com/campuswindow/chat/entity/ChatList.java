package com.campuswindow.chat.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chat_list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatList {

    @Id
    //列表id（自增）
    private String listId;
    //关系表主键
    private String linkId;
    //发送者
    private String fromUserId;
    //接收者
    private String toUserId;
    private String toUserName;
    private String toUserAvatar;
    private String lastMsg;
    //发送者是否在窗口
//    private Boolean fromWindow;
//    //接收者是否在窗口
//    private Boolean toWindow;
    //未读数 fromUser的未读数
    private int unread;
    //是否被删除
    private Boolean status;

}

