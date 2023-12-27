package com.campuswindow.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatChannelDto {
    private String channelName;
    private String channelAvatar;
    private String channelSignature;
    private String parentId;
    private String channelMaster;
    private String channelBackground;
}
