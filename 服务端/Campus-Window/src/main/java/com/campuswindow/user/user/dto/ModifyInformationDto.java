package com.campuswindow.user.user.dto;

import lombok.Data;

@Data
public class ModifyInformationDto {
    private String userId;
    private String userName;
    private int gender;
    private String signature;
}
