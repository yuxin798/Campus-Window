package com.campuswindow.user.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FansVo {
    private String userId;
    private String userName;
    private String avatar;
    private String signature;
    private boolean mutualFollow;

    public FansVo(String userId, String userName, String avatar, String signature) {
        this.userId = userId;
        this.userName = userName;
        this.avatar = avatar;
        this.signature = signature;
    }
}
