package com.greenmist.persistence.mapper;

import com.greenmist.model.AuthToken;

/**
 * Created by eckob on 10/5/2016.
 */
public interface AuthTokenMapper {

    void updateAuthToken(AuthToken authToken);

    void insertAuthToken(AuthToken authToken);

    AuthToken getAuthToken(AuthToken authToken);

    void deleteAuthToken(AuthToken authToken);

    void deleteExpiredAuthTokens();
}
