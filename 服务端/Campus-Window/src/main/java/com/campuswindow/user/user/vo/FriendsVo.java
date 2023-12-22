package com.campuswindow.user.user.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendsVo {
    private String userId;
    private String userName;
    private String avatar;
    private String signature;
}
