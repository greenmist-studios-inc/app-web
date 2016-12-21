package com.greenmist.service;

import com.greenmist.exception.AuthorizationException;
import com.greenmist.exception.ErrorException;
import com.greenmist.exception.UserInvalidException;
import com.greenmist.exception.code.ErrorCode;
import com.greenmist.model.AuthToken;
import com.greenmist.model.User;
import com.greenmist.rest.response.SessionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eckob on 10/29/2016.
 */
@Service
public class AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    private UserService userService;
    private AuthTokenService authTokenService;
    private ResetPasswordTokenService resetPasswordTokenService;

    @Autowired
    public AccountService(UserService userService, AuthTokenService authTokenService, ResetPasswordTokenService resetPasswordTokenService) {
        this.userService = userService;
        this.authTokenService = authTokenService;
        this.resetPasswordTokenService = resetPasswordTokenService;
    }

    public SessionResponse createUser(User user, String password) throws ErrorException {

        userService.insertUser(user, password);
        userService.updatePassword(user, password);

        try {
            AuthToken token = authTokenService.authenticateUser(user.getEmail(), password);
            return new SessionResponse(user.getEmail(), token);
        }
        catch (ErrorException ae) {
            LOGGER.error("Created user but unable to authenticate. Exception - " + ae.toString());
            throw new AuthorizationException();
        }
    }

    public void initiateForgotPassword(String email) throws ErrorException {
        resetPasswordTokenService.initiatePasswordResetUser(email);
        //TODO Send email
    }

    public void updatePassword(String resetPasswordToken, String password) throws AuthorizationException, UserInvalidException {
        User user = userService.getUserFromResetPasswordToken(resetPasswordToken);
        if (user == null) {
            throw new UserInvalidException("Could not update user. User doesn't exist.");
        } else {
            userService.updatePassword(user, password);
            resetPasswordTokenService.deleteResetPasswordTokenByUserId(user.getId());
        }
    }

    public User updateUser(String token, String email, String firstName, String lastName) throws UserInvalidException, AuthorizationException {
        User user = userService.getUserFromAuthToken(token);
        return userService.updateUser(user, email, firstName, lastName);
    }
}