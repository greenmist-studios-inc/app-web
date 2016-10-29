package com.greenmist.model;

/**
 * Created by eckob on 10/23/2016.
 */
public class AuthToken {

    private long userId;
    private String token;

    public AuthToken() {
    }

    public AuthToken(long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
