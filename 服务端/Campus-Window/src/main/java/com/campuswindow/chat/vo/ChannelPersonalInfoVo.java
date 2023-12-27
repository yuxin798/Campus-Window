package com.campuswindow.chat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelPersonalInfoVo {
    private String userName;
    private String userAvatar;
    private String school;
    private int gender;
    private List<ChannelInfoVo> myselfChannels;
    private List<ChannelInfoVo> otherChannels;

    public ChannelPersonalInfoVo(String userName, String userAvatar, String school, int gender) {
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.school = school;
        this.gender = gender;
    }
}
