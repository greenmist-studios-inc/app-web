package com.greenmist.exception;

import com.greenmist.exception.code.ErrorCode;

/**
 * Created by eckob on 10/5/2016.
 */
@SuppressWarnings("ThrowableInstanceNeverThrown")
public class ErrorException extends Exception {

    public static final ErrorException EMPTY_REQUESTS = new ErrorException(ErrorCode.INVALID_REQUEST, "Your request was empty.");
    public static final ErrorException MALFORMED_REQUEST = new ErrorException(ErrorCode.INVALID_REQUEST, "Your request was malformed.");

    private ErrorCode errorCode;
    private String[] errorDetails;

    public ErrorException(ErrorCode errorCode, String... errorDetails) {
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String[] getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String[] errorDetails) {
        this.errorDetails = errorDetails;
    }
}
