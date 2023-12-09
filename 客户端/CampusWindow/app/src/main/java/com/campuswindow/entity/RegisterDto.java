package com.campuswindow.entity;


public class RegisterDto {
    private String userName;
    private String email;
    private String password;
    private String school;
    private String emailCode;
    private String emailCodeKey;

    public RegisterDto() {
    }

    public RegisterDto(String userName, String email, String password, String school, String emailCode, String emailCodeKey) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.school = school;
        this.emailCode = emailCode;
        this.emailCodeKey = emailCodeKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
    }

    public String getEmailCodeKey() {
        return emailCodeKey;
    }

    public void setEmailCodeKey(String emailCodeKey) {
        this.emailCodeKey = emailCodeKey;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
