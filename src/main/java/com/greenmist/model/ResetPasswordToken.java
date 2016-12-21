package com.greenmist.model;

/**
 * Created by eckob on 10/23/2016.
 */
public class ResetPasswordToken {

    private int userId;
    private String token;

    public ResetPasswordToken() {
    }

    public ResetPasswordToken(int userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
