package com.greenmist.service;

import com.greenmist.exception.ErrorException;
import com.greenmist.exception.UnauthorizedException;
import com.greenmist.model.AuthToken;
import com.greenmist.rest.response.SessionResponse;

/**
 * Created by eckob on 10/29/2016.
 */
public interface SessionService {

    SessionResponse login(String email, String password) throws ErrorException;

    AuthToken checkAuthToken(int accountId, String token) throws UnauthorizedException;

    void logout(AuthToken authToken);
}
