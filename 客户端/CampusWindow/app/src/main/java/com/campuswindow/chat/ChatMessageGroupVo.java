package com.campuswindow.chat;

import java.io.Serializable;
import java.util.Date;

public class ChatMessageGroupVo implements Serializable {
    //文本
    public static final int MESSAGE_TYPE_TEXT = 0;
    //图片
    public static final int MESSAGE_TYPE_IMAGE = 1;
    //信息id（自增）
    private String messageId;
    //关系表id
    private String linkId;
    private String userId;
    private String userName;
    private String avatar;
    //内容
    private String content;
    //发送时间
    private Date sendTime;
    //消息类型  0--普通文本（默认）
    private int type = MESSAGE_TYPE_TEXT;

    public ChatMessageGroupVo() {
    }

    public ChatMessageGroupVo(String messageId, String linkId, String userId, String userName, String avatar, String content, Date sendTime, int type) {
        this.messageId = messageId;
        this.linkId = linkId;
        this.userId = userId;
        this.userName = userName;
        this.avatar = avatar;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ChatMessageGroupVo{" +
                "messageId='" + messageId + '\'' +
                ", linkId='" + linkId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", content='" + content + '\'' +
                ", sendTime=" + sendTime +
                ", type=" + type +
                '}';
    }
}
