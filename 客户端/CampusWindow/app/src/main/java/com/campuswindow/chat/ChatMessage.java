package com.campuswindow.chat;

import java.sql.Timestamp;

/*
 * 聊天信息的从后端获取的实体类
 */

public class ChatMessage {
    //文本
    public static final int MESSAGE_TYPE_TEXT = 0;

    //图片
    public static final int MESSAGE_TYPE_IMAGE = 1;

    //信息id（自增）
    private String messageId;
    //关系表id
    private String linkId;
    //发送者
    private String fromUserId;
    //接收者
    private String toUserId;
    //内容
    private String content;
    private Timestamp sendTime;
    //消息类型  0--普通文本（默认）
    private int type = MESSAGE_TYPE_TEXT;
    //是否为最后一条

    public ChatMessage() {
    }

    public ChatMessage(String messageId, String linkId, String fromUserId, String toUserId, String content, Timestamp sendTime, int type) {
        this.messageId = messageId;
        this.linkId = linkId;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.content = content;
        this.sendTime = sendTime;
        this.type = type;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}

