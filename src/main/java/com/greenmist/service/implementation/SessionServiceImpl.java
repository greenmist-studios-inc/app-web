package com.greenmist.service.implementation;

import com.greenmist.exception.ErrorException;
import com.greenmist.exception.UnauthorizedException;
import com.greenmist.exception.code.ErrorCode;
import com.greenmist.model.AuthToken;
import com.greenmist.model.User;
import com.greenmist.rest.response.SessionResponse;
import com.greenmist.service.AuthTokenService;
import com.greenmist.service.SessionService;
import com.greenmist.service.UserService;
import com.greenmist.utils.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by eckob on 10/29/2016.
 */
@Service("sessionService")
public class SessionServiceImpl implements SessionService {

    private final AuthTokenService authTokenService;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SessionServiceImpl(AuthTokenService authTokenService, UserService userService) {
        this.authTokenService = authTokenService;
        this.userService = userService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public SessionResponse login(String email, String password) throws ErrorException {
        User user = userService.getUserByEmail(email);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            AuthToken authToken = new AuthToken();
            authToken.setUserId(user.getId());
            authToken.setToken(TokenGenerator.generateToken());

            authTokenService.insertAuthToken(authToken);
            return new SessionResponse(email, authToken);
        } else {
            throw new ErrorException(ErrorCode.LOGIN_ERROR, null);
        }
    }

    @Override
    public AuthToken checkAuthToken(int accountId, String token) throws UnauthorizedException {
        AuthToken authToken = authTokenService.getAuthToken(new AuthToken(accountId, token));
        if (authToken != null) {
            authTokenService.updateAuthToken(authToken);
            return authToken;
        } else {
            throw new UnauthorizedException("Auth token is invalid.");
        }
    }

    @Override
    public void logout(AuthToken authToken) {
        authTokenService.deleteAuthToken(authToken);
    }
}
