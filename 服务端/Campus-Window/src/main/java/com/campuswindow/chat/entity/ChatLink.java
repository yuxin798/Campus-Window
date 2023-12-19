package com.campuswindow.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "chat_link")
@AllArgsConstructor
@NoArgsConstructor
public class ChatLink {
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
