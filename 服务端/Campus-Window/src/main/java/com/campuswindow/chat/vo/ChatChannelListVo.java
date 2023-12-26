package com.campuswindow.chat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatChannelListVo {
    private String listId;
    //关系表主键
    private String linkId;
    //接收者
    private String name;
    private String avatar;
    private String lastMsg;
    //未读数
    private int unread;

    public ChatChannelListVo(String listId, String linkId, String name, String avatar, String lastMsg) {
        this.listId = listId;
        this.linkId = linkId;
        this.name = name;
        this.avatar = avatar;
        this.lastMsg = lastMsg;
    }
}
