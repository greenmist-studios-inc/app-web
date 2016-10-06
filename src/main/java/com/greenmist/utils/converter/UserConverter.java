package com.greenmist.utils.converter;

import com.greenmist.exception.UserInvalidException;
import com.greenmist.model.User;
import com.greenmist.rest.request.RegisterUserRequest;

/**
 * Created by eckob on 10/5/2016.
 */
public class UserConverter {

    public static User to(RegisterUserRequest registerUserRequest) throws Exception {
        if (registerUserRequest != null
                && StringUtils.isNotBlank(registerUserRequest.getFirstName())
                && StringUtils.isNotBlank(registerUserRequest.getLastName())
                && StringUtils.isNotBlank(registerUserRequest.getEmail())) {
            User user = new User();
            user.setFirstName(registerUserRequest.getFirstName());
            user.setLastName(registerUserRequest.getLastName());
            user.setEmail(registerUserRequest.getEmail());
            return user;
        } else if (registerUserRequest == null) {
            throw UserInvalidException.EMPTY_REQUESTS;
        } else {
            throw UserInvalidException.MALFORMED_REQUEST;
        }
    }

}
