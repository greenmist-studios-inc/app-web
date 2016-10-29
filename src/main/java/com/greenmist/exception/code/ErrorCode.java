package com.greenmist.exception.code;

import org.springframework.http.HttpStatus;

/**
 * Created by eckob on 10/5/2016.
 */
public enum ErrorCode {
    BASE_ERROR(0, "Something went wrong", HttpStatus.NOT_FOUND),
    INVALID_REQUEST(1, "Your request is invalid.", HttpStatus.BAD_REQUEST),
    EMAIL_TAKEN_REQUEST(2, "The email provided has already been taken.", HttpStatus.NOT_FOUND),
    LOGIN_ERROR(3, "The email or password provided was incorrect.", HttpStatus.NOT_FOUND),
    UNAUTHORIZED(4, "Unauthorized", HttpStatus.UNAUTHORIZED);

    private int errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;

    ErrorCode(int errorCode, String errorMessage, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
