package com.campuswindow.entity;

public class ModifyInformationDto {
    private String userId;
    private String userName;
    private int gender;
    private String signature;

    public ModifyInformationDto(String userId, String userName, int gender, String signature) {
        this.userId = userId;
        this.userName = userName;
        this.gender = gender;
        this.signature = signature;
    }

    public ModifyInformationDto() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "modifyInformationDto{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", gender=" + gender +
                ", signature='" + signature + '\'' +
                '}';
    }
}
