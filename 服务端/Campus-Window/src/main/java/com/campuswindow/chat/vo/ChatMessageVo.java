package com.campuswindow.chat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageVo {
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
}
