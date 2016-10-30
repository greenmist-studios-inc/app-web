package com.greenmist.service;

import com.greenmist.exception.ErrorException;
import com.greenmist.exception.AuthorizationException;
import com.greenmist.model.AuthToken;
import com.greenmist.rest.response.SessionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by eckob on 10/29/2016.
 */
@Service
public class SessionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionService.class);

    private final AuthTokenService authTokenService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SessionService(AuthTokenService authTokenService) {
        this.authTokenService = authTokenService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public SessionResponse login(String email, String password) throws ErrorException {
        AuthToken authToken = authTokenService.authenticateUser(email, password);
        return new SessionResponse(email, authToken);
    }

    public AuthToken checkAuthToken(int userId, String token) throws AuthorizationException {
        return authTokenService.checkAuthToken(new AuthToken(userId, token));
    }

    public void logout(AuthToken authToken) {
        authTokenService.deleteAuthToken(authToken);
    }
}
