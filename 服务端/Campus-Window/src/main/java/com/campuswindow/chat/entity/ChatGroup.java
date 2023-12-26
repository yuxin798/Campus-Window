package com.campuswindow.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "chat_group")
@AllArgsConstructor
@NoArgsConstructor
public class ChatGroup {
    @Id
    private String linkId;
    private String groupName;
    private String groupAvatar;
    private int groupNumber;
}
