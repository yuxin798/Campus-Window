package com.campuswindow.chat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelHomePageVo {
    private String linkId;
    private String channelName;
    private String channelAvatar;
    private String channelSignature;
    private String channelBackground;
    private int channelNumber;

    public ChannelHomePageVo(String linkId, String channelName, String channelAvatar, String channelSignature, String channelBackground) {
        this.linkId = linkId;
        this.channelName = channelName;
        this.channelAvatar = channelAvatar;
        this.channelSignature = channelSignature;
        this.channelBackground = channelBackground;
    }
}
