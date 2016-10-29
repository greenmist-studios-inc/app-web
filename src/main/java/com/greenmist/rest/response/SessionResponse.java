package com.greenmist.rest.response;

import com.greenmist.model.AuthToken;

/**
 * Created by eckob on 10/29/2016.
 */
public class SessionResponse {

    private String email;
    private AuthToken token;

    public SessionResponse(String email, AuthToken token) {
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AuthToken getToken() {
        return token;
    }

    public void setToken(AuthToken token) {
        this.token = token;
    }
}
