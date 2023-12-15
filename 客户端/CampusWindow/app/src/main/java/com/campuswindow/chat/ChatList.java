package com.campuswindow.chat;


/*
 * 聊天列表的实体类
 */
public class ChatList {
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
    //发送者是否在窗口
    private int fromWindow;
    //接收者是否在窗口
    private int toWindow;
    private String lastMsg;
    //未读数 fromUser的未读数
    private int unread;
    //是否被删除
    private int status;

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
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

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getToUserAvatar() {
        return toUserAvatar;
    }

    public void setToUserAvatar(String toUserAvatar) {
        this.toUserAvatar = toUserAvatar;
    }

    public int getFromWindow() {
        return fromWindow;
    }

    public void setFromWindow(int fromWindow) {
        this.fromWindow = fromWindow;
    }

    public int getToWindow() {
        return toWindow;
    }

    public void setToWindow(int toWindow) {
        this.toWindow = toWindow;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public int getUnread() {
        return unread;
    }

    public void setUnread(int unread) {
        this.unread = unread;
    }

    public ChatList() {
    }

    public ChatList(String listId, String linkId, String fromUserId, String toUserId, String toUserName, String toUserAvatar, int fromWindow, int toWindow, String lastMsg, int unread, int status) {
        this.listId = listId;
        this.linkId = linkId;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.toUserName = toUserName;
        this.toUserAvatar = toUserAvatar;
        this.fromWindow = fromWindow;
        this.toWindow = toWindow;
        this.lastMsg = lastMsg;
        this.unread = unread;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ChatList{" +
                "listId='" + listId + '\'' +
                ", linkId='" + linkId + '\'' +
                ", fromUserId='" + fromUserId + '\'' +
                ", toUserId='" + toUserId + '\'' +
                ", toUserName='" + toUserName + '\'' +
                ", toUserAvatar='" + toUserAvatar + '\'' +
                ", fromWindow=" + fromWindow +
                ", toWindow=" + toWindow +
                ", lastMsg='" + lastMsg + '\'' +
                ", unread=" + unread +
                ", status=" + status +
                '}';
    }
}

