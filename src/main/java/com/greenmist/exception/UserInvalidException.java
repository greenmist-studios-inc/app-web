package com.greenmist.exception;

import com.greenmist.exception.code.ErrorCode;

/**
 * Created by eckob on 10/5/2016.
 */
@SuppressWarnings("ThrowableInstanceNeverThrown")
public class UserInvalidException extends ErrorException {

    public UserInvalidException(String details) {
        super(ErrorCode.INVALID_REQUEST, details);
    }
}
