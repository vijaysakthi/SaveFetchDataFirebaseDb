package com.example.vijay.savefetchdatafirebasedb;

public class Users {
    String userId, userName, userMobile;

    public Users() {

    }


    public Users(String userId, String userName, String userMobile) {
        this.userId = userId;
        this.userName = userName;
        this.userMobile = userMobile;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserMobile() {
        return userMobile;
    }
}
