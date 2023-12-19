package com.campuswindow.chat.group.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "chat_link_group")
@AllArgsConstructor
@NoArgsConstructor
public class ChatLinkGroup {
    @Id
    private String id;
    //关系表id
    private String linkId;
    private Timestamp createTime;
    private int type;
    private String name;
    private String avatar;
    private int num;
}
