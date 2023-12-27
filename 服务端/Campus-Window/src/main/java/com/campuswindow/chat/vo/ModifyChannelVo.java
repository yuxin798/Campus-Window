package com.campuswindow.chat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyChannelVo {
    private String linkId;
    private String channelName;
    private String channelAvatar;
    private String channelSignature;
    private String channelBackground;
}
