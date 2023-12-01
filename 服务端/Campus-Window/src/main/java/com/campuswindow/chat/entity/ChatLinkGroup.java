package com.campuswindow.chat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "chat_user_link_group")
@Data
public class ChatLinkGroup {
    @Id
    private String publicId;
    //关系表id
    private String groupId;
    //发送者
    private String userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;
}
