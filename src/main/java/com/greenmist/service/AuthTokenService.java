package com.greenmist.service;

import com.greenmist.model.AuthToken;

/**
 * Created by eckob on 10/23/2016.
 */
public interface TokenService {

    AuthToken authenticateUser(String email, String password);

    AuthToken checkAuthToken(AuthToken authToken);

    void deleteAuthToken(String token);

    void updateAuthToken(AuthToken authToken);

    void insertAuthToken(AuthToken authToken);

    void deleteExpiredAuthTokens();
}
