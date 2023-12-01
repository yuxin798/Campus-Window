package com.campuswindow.chat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "chat_user_link")
@Data
public class ChatLink {
    @Id
    //关系表id
    private String linkId;
    //发送者
    private String fromUserId;
    //接收者
    private String toUserId;
    private String groupId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;

}
