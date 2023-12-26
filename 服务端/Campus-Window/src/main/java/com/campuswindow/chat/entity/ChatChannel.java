package com.campuswindow.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "chat_channel")
@AllArgsConstructor
@NoArgsConstructor
public class ChatChannel {
    @Id
    private String linkId;
    private String channelName;
    private String channelAvatar;
    private String channelSignature;
    private String parentId;
    private String channelMaster;
}
