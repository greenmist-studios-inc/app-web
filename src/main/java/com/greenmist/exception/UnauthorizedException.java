package com.greenmist.exception;

import com.greenmist.exception.code.ErrorCode;

/**
 * Created by eckob on 10/29/2016.
 */
public class UnauthorizedException extends ErrorException {
    public UnauthorizedException(String... errorDetails) {
        super(ErrorCode.UNAUTHORIZED, errorDetails);
    }
}
