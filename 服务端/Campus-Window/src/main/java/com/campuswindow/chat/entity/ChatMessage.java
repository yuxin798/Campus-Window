package com.campuswindow.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "chat_message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    //文本
    public static final int MESSAGE_TYPE_TEXT = 0;
    //图片
    public static final int MESSAGE_TYPE_IMAGE = 1;
    @Id
    //信息id（自增）
    private String messageId;
    //关系表id
    private String linkId;
    private String userId;
    //内容
    private String content;
    //发送时间
    private Timestamp sendTime;
    //消息类型  0--普通文本（默认）
    private int type = MESSAGE_TYPE_TEXT;
}
