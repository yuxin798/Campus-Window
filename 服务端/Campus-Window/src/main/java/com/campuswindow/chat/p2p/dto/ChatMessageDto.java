package com.campuswindow.chat.p2p.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {
    private String fromUserId;
    //接收者
    private String toUserId;
    //内容
    private String content;

}
