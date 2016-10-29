package com.greenmist.service.implementation;

import com.greenmist.model.AuthToken;
import com.greenmist.persistence.mapper.AuthTokenMapper;
import com.greenmist.service.AuthTokenService;
import com.greenmist.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eckob on 10/23/2016.
 */
@Service("tokenService")
public class AuthTokenServiceImpl implements AuthTokenService {

    private final AuthTokenMapper authTokenMapper;

    @Autowired
    public AuthTokenServiceImpl(AuthTokenMapper authTokenMapper) {
        this.authTokenMapper = authTokenMapper;
    }

    @Override
    public AuthToken getAuthToken(AuthToken authToken) {
        return authTokenMapper.getAuthToken(authToken);
    }

    @Override
    public void deleteAuthToken(AuthToken authToken) {
        authTokenMapper.deleteAuthToken(authToken);
    }

    @Override
    public void updateAuthToken(AuthToken authToken) {
        if (StringUtils.isNotBlank(authToken.getToken())) {
            authTokenMapper.updateAuthToken(authToken);
        }
    }

    @Override
    public void insertAuthToken(AuthToken authToken) {
        authTokenMapper.insertAuthToken(authToken);
    }

    @Override
    public void deleteExpiredAuthTokens() {
        authTokenMapper.deleteExpiredAuthTokens();
    }
}
