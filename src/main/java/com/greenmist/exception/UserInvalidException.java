package com.greenmist.exception;

import com.greenmist.exception.code.ErrorCode;

/**
 * Created by eckob on 10/5/2016.
 */
@SuppressWarnings("ThrowableInstanceNeverThrown")
public class UserInvalidException extends ErrorException {

    public static final UserInvalidException EMPTY_REQUESTS = new UserInvalidException("Your request was empty.");
    public static final UserInvalidException MALFORMED_REQUEST = new UserInvalidException("Your request was malformed.");

    public UserInvalidException(String details) {
        super(ErrorCode.INVALID_USER_REQUEST, details);
    }
}
