package com.greenmist.service;

import com.greenmist.model.AuthToken;

/**
 * Created by eckob on 10/23/2016.
 */
public interface AuthTokenService {

    AuthToken getAuthToken(AuthToken authToken);

    void deleteAuthToken(AuthToken authToken);

    void updateAuthToken(AuthToken authToken);

    void insertAuthToken(AuthToken authToken);

    void deleteExpiredAuthTokens();
}
