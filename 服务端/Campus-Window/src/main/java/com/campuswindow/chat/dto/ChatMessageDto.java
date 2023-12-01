package com.campuswindow.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {
    private String sendUserId;
    //信息内容
    private String content;
    //发送时间
    private Timestamp sendTime;
}
