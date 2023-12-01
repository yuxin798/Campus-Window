package com.campuswindow.chat.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tbl_msg")
public class Msg {
    @Id
    private String msgId;
    private String content;//发送的内容
    private boolean isSend;
    private String userId;
    private String userName;
    private String avatar;
//    private int type;//1私发 2群发
//    private String toUserId;

}
