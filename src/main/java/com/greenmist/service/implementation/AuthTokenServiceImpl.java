package com.greenmist.service.implementation;

import com.greenmist.model.AuthToken;
import com.greenmist.model.User;
import com.greenmist.service.TokenService;
import com.greenmist.service.UserService;
import com.greenmist.utils.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import static org.apache.coyote.http11.Constants.a;

/**
 * Created by eckob on 10/23/2016.
 */
@Service("tokenService")
public class TokenServiceImpl implements TokenService {

    private final UserService userService;
    private final AuthTokenMapper authTokenMapper;

    @Autowired
    public AuthTokenService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public AuthToken authenticateUser(String email, String password) {
        User user = userService.getUserByEmail(email);

        if (user != null) {
            AuthToken authToken = new AuthToken();
            authToken.setAccountId(user.getId());
            authToken.setToken(TokenGenerator.generateToken());

            insertAuthToken(authToken);
            return authToken;
        }
        return null;
    }

    @Override
    public AuthToken checkAuthToken(AuthToken authToken) {
        return null;
    }

    @Override
    public void deleteAuthToken(String token) {

    }

    @Override
    public void updateAuthToken(AuthToken authToken) {

    }

    @Override
    public void insertAuthToken(AuthToken authToken) {

    }

    @Override
    public void deleteExpiredAuthTokens() {

    }
}
