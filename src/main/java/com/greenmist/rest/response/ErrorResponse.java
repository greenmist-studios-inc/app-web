package com.greenmist.rest.response;

import com.greenmist.exception.ErrorException;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by eckob on 10/23/2016.
 */
public class ErrorResponse implements Serializable {

    private int errorCode;
    private String errorMessage;
    private String[] details;

    public ErrorResponse(ErrorException errorException) {
        errorCode = errorException.getErrorCode().getErrorCode();
        errorMessage = errorException.getErrorCode().getErrorMessage();
        details = errorException.getErrorDetails();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String[] getDetails() {
        return details;
    }

    public void setDetails(String[] details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                ", details=" + Arrays.toString(details) +
                '}';
    }
}
