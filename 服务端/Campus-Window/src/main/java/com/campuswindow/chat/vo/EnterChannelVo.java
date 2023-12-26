package com.campuswindow.chat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnterChannelVo {
    private String linkId;
    private String lastMsg;
    private Date lastMsgTime;
    //未读数
    private long unread;
}
