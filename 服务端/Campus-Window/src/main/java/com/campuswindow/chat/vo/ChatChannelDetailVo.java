package com.campuswindow.chat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatChannelDetailVo {
    private String linkId;
    private String channelName;
    private String channelAvatar;
    private String channelBackground;
    private String channelMaster;
    private int channelNumber;
    private List<ChatChannelListVo> chatChannelListVos;

    public ChatChannelDetailVo(String linkId, String channelName, String channelAvatar, String channelBackground, String channelMaster) {
        this.linkId = linkId;
        this.channelName = channelName;
        this.channelAvatar = channelAvatar;
        this.channelBackground = channelBackground;
        this.channelMaster = channelMaster;
    }
}
