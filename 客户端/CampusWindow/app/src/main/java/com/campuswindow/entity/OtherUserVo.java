package com.campuswindow.entity;

public class OtherUserVo {
    private String userId;
    private String userName;
    private String school;
    private String avatar;
    private double gender;
    private String signature;
    private double loves;
    private double friends;
    private double followers;
    private double fans;
    private boolean followed;
    private String background;

    public OtherUserVo() {
    }

    public OtherUserVo(String userId, String userName, String school, String avatar, double gender, String signature, double loves, double friends, double followers, double fans, boolean followed, String background) {
        this.userId = userId;
        this.userName = userName;
        this.school = school;
        this.avatar = avatar;
        this.gender = gender;
        this.signature = signature;
        this.loves = loves;
        this.friends = friends;
        this.followers = followers;
        this.fans = fans;
        this.followed = followed;
        this.background = background;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public double getGender() {
        return gender;
    }

    public void setGender(double gender) {
        this.gender = gender;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public double getLoves() {
        return loves;
    }

    public void setLoves(double loves) {
        this.loves = loves;
    }

    public double getFriends() {
        return friends;
    }

    public void setFriends(double friends) {
        this.friends = friends;
    }

    public double getFollowers() {
        return followers;
    }

    public void setFollowers(double followers) {
        this.followers = followers;
    }

    public double getFans() {
        return fans;
    }

    public void setFans(double fans) {
        this.fans = fans;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
