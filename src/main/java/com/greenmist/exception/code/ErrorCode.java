package com.greenmist.exception.code;

/**
 * Created by eckob on 10/5/2016.
 */
public enum ErrorCode {
    BASE_ERROR(0, "Something went wrong"),
    INVALID_USER_REQUEST(1, "Your request is invalid.");

    private int errorCode;
    private String errorMessage;

    ErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
