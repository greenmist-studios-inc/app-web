package com.greenmist.service;

import com.greenmist.exception.ErrorException;
import com.greenmist.exception.AuthorizationException;
import com.greenmist.exception.code.ErrorCode;
import com.greenmist.model.AuthToken;
import com.greenmist.model.User;
import com.greenmist.persistence.mapper.AuthTokenMapper;
import com.greenmist.rest.response.SessionResponse;
import com.greenmist.utils.StringUtils;
import com.greenmist.utils.TokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by eckob on 10/23/2016.
 */
@Service
public class AuthTokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthTokenService.class);

    private final AuthTokenMapper authTokenMapper;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthTokenService(AuthTokenMapper authTokenMapper, UserService userService) {
        this.authTokenMapper = authTokenMapper;
        this.userService = userService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public AuthToken authenticateUser(String email, String password) throws ErrorException {
        User user = userService.getUserByEmail(email);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            AuthToken authToken = new AuthToken();
            authToken.setUserId(user.getId());
            authToken.setToken(TokenGenerator.generateToken());

            insertAuthToken(authToken);
            userService.updateLastLogin(authToken.getUserId());
            return authToken;
        } else {
            throw new ErrorException(ErrorCode.LOGIN_ERROR);
        }
    }

    public AuthToken checkAuthToken(AuthToken authToken) throws AuthorizationException {
        if (authToken != null) {
            boolean tokenUpdated = updateAuthToken(authToken);
            if (tokenUpdated) {
                return authToken;
            } else {
                throw new AuthorizationException("Auth token is invalid.");
            }
        } else {
            throw new AuthorizationException("Auth token is invalid.");
        }
    }

    public AuthToken getAuthToken(AuthToken authToken) {
        return authTokenMapper.getAuthToken(authToken);
    }

    public void deleteAuthToken(AuthToken authToken) {
        authTokenMapper.deleteAuthToken(authToken);
    }

    public boolean updateAuthToken(AuthToken authToken) throws AuthorizationException {
        if (StringUtils.isNotBlank(authToken.getToken())) {
            return authTokenMapper.updateAuthToken(authToken);
        } else {
            LOGGER.debug("Auth token %s is invalid.", authToken.getToken());
            throw new AuthorizationException("Auth token is invalid.");
        }
    }

    public void insertAuthToken(AuthToken authToken) {
        authTokenMapper.insertAuthToken(authToken);
    }

    public void deleteExpiredAuthTokens() {
        authTokenMapper.deleteExpiredAuthTokens();
    }
}
