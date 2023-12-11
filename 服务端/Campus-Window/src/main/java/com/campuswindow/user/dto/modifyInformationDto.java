package com.campuswindow.user.dto;

import lombok.Data;

@Data
public class modifyInformationDto {
    private String userId;
    private String userName;
    private int gender;
    private String signature;
}
