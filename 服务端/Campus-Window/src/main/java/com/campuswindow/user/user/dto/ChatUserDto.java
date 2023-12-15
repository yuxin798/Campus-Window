package com.campuswindow.user.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatUserDto {
    private String userId;
    private String userName;
    private String avatar;
}
