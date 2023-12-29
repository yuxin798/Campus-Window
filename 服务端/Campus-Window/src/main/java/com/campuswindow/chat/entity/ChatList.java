package com.campuswindow.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

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
    private String userId;
    //用户是否在窗口
    private int window;
    private String lastMsg;
    private Timestamp lastMsgTime;
    //未读数 fromUser的未读数
    private int unread;
    //是否被删除 false代表删除  true代表未删除
    private int status;
    private String background;
}
