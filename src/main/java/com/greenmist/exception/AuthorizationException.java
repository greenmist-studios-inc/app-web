package com.greenmist.exception;

import com.greenmist.exception.code.ErrorCode;

/**
 * Created by eckob on 10/29/2016.
 */
public class AuthorizationException extends ErrorException {
    public AuthorizationException(String... errorDetails) {
        super(ErrorCode.UNAUTHORIZED, errorDetails);
    }
}
