package com.greenmist.service;

import com.greenmist.exception.AuthorizationException;
import com.greenmist.exception.ErrorException;
import com.greenmist.exception.code.ErrorCode;
import com.greenmist.model.ResetPasswordToken;
import com.greenmist.model.User;
import com.greenmist.persistence.mapper.ResetPasswordTokenMapper;
import com.greenmist.utils.StringUtils;
import com.greenmist.utils.TokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eckob on 10/23/2016.
 */
@Service
public class ResetPasswordTokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResetPasswordTokenService.class);

    private final ResetPasswordTokenMapper authTokenMapper;
    private final UserService userService;

    @Autowired
    public ResetPasswordTokenService(ResetPasswordTokenMapper authTokenMapper, UserService userService) {
        this.authTokenMapper = authTokenMapper;
        this.userService = userService;
    }

    public void initiatePasswordResetUser(String email) throws ErrorException {
        User user = userService.getUserByEmail(email);

        if (user != null) {
            ResetPasswordToken authToken = new ResetPasswordToken();
            authToken.setUserId(user.getId());
            authToken.setToken(TokenGenerator.generateToken());

            insertResetPasswordToken(authToken);
        } else {
            throw new ErrorException(ErrorCode.INVALID_EMAIL_ERROR);
        }
    }

    public ResetPasswordToken checkResetPasswordToken(ResetPasswordToken authToken) throws AuthorizationException {
        if (authToken != null) {
            boolean tokenUpdated = isValidResetPasswordToken(authToken);
            if (tokenUpdated) {
                return authToken;
            } else {
                throw new AuthorizationException("Reset password token is invalid.");
            }
        } else {
            throw new AuthorizationException("Reset password token is invalid.");
        }
    }

    public ResetPasswordToken getResetPasswordToken(ResetPasswordToken authToken) {
        return authTokenMapper.getResetPasswordToken(authToken);
    }

    public void deleteResetPasswordTokenByUserId(int userId) {
        authTokenMapper.deleteResetPasswordTokenByUserId(userId);
    }

    public boolean isValidResetPasswordToken(ResetPasswordToken authToken) throws AuthorizationException {
        if (StringUtils.isNotBlank(authToken.getToken())) {
            return authTokenMapper.isValidResetPasswordToken(authToken);
        } else {
            LOGGER.debug("Reset password token %s is invalid.", authToken.getToken());
            throw new AuthorizationException("Reset password token is invalid.");
        }
    }

    public void insertResetPasswordToken(ResetPasswordToken authToken) {
        authTokenMapper.insertResetPasswordToken(authToken);
    }

    public void deleteExpiredResetPasswordTokens() {
        authTokenMapper.deleteExpiredResetPasswordTokens();
    }
}
