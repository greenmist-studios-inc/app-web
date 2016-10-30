package com.greenmist.exception.code;

import org.springframework.http.HttpStatus;

/**
 * Created by eckob on 10/5/2016.
 */
public enum ErrorCode {
    BASE_ERROR(0, "Something went wrong", HttpStatus.NOT_FOUND),
    INVALID_REQUEST(1, "Your request is invalid.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(2, "Unauthorized", HttpStatus.UNAUTHORIZED),
    MISSING_AUTHENTICATION_HEADERS(3, "Missing authentication headers.", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN (4, "Invalid Token.", HttpStatus.UNAUTHORIZED),
    INVALID_ACCOUNT_ID (5, "Invalid Account ID.", HttpStatus.UNAUTHORIZED),
    EMAIL_TAKEN_REQUEST(6, "The email provided has already been taken.", HttpStatus.NOT_FOUND),
    LOGIN_ERROR(7, "The email or password provided was incorrect.", HttpStatus.NOT_FOUND),
    REGISTER_USER_ERROR(8, "Could not create user.", HttpStatus.NOT_FOUND);

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
