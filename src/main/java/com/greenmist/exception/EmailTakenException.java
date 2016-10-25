package com.greenmist.exception;

import com.greenmist.exception.code.ErrorCode;

/**
 * Created by eckob on 10/5/2016.
 */
@SuppressWarnings("ThrowableInstanceNeverThrown")
public class EmailTakenException extends ErrorException {

    public EmailTakenException(String details) {
        super(ErrorCode.EMAIL_TAKEN_REQUEST, details);
    }
}
