package com.campuswindow.user.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowersVo {
    private String userId;
    private String userName;
    private String avatar;
    private String signature;
    //是否互相关注
    private boolean mutualFollow;

    public FollowersVo(String userId, String userName, String avatar, String signature) {
        this.userId = userId;
        this.userName = userName;
        this.avatar = avatar;
        this.signature = signature;
    }
}
