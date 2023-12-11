package com.campuswindow.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyInformationVo {
    private String userId;
    private String userName;
    private int gender;
    private String signature;
}
