package com.greenmist.exception;

import com.greenmist.exception.code.ErrorCode;

/**
 * Created by eckob on 10/5/2016.
 */
public class ErrorException extends Exception {

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
