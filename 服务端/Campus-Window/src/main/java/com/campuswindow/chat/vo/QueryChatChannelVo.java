package com.campuswindow.chat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryChatChannelVo {
    private String linkId;
    private String channelName;
    private String channelAvatar;
    private String channelSignature;
    private int channelNumber;
    private Boolean entered;

    public QueryChatChannelVo(String linkId, String channelName, String channelAvatar, String channelSignature) {
        this.linkId = linkId;
        this.channelName = channelName;
        this.channelAvatar = channelAvatar;
        this.channelSignature = channelSignature;
    }
}
