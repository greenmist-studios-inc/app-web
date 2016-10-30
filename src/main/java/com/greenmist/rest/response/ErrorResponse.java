package com.greenmist.rest.response;

import com.greenmist.exception.ErrorException;
import com.greenmist.exception.code.ErrorCode;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.Response;
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

    protected ErrorResponse(ErrorCode errorCode, String[] details) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
        this.details = details;
    }

    public static final ErrorResponseBuilder badRequest(ErrorCode errorCode) {
        return new ErrorResponseBuilder(HttpStatus.BAD_REQUEST, errorCode);
    }

    public static final ErrorResponseBuilder internalServerError(ErrorCode errorCode) {
        return new ErrorResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, errorCode);
    }

    public static final ErrorResponseBuilder notFound(ErrorCode errorCode) {
        return new ErrorResponseBuilder(HttpStatus.NOT_FOUND, errorCode);
    }

    public static final ErrorResponseBuilder unauthorized(ErrorCode errorCode) {
        return new ErrorResponseBuilder(HttpStatus.UNAUTHORIZED, errorCode);
    }

    public static final ErrorResponseBuilder forbidden(ErrorCode errorCode) {
        return new ErrorResponseBuilder(HttpStatus.FORBIDDEN, errorCode);
    }

    public static final ErrorResponseBuilder custom(HttpStatus status, ErrorCode errorCode) {
        return new ErrorResponseBuilder(status, errorCode);
    }

    public static final Response custom(int status, ErrorResponse errorResponse) {
        return Response.status(status)
                .entity(errorResponse)
                .build();
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

    public static final class ErrorResponseBuilder {
        private final HttpStatus httpStatus;
        private final ErrorCode errorCode;
        private String[] details;

        public Response build() {
            return Response.status(httpStatus.value())
                    .entity(new ErrorResponse(errorCode, details))
                    .build();
        }

        private ErrorResponseBuilder(HttpStatus httpStatus, ErrorCode errorCode) {
            this.httpStatus = httpStatus;
            this.errorCode = errorCode;
        }

        public ErrorResponseBuilder details(String... details) {
            this.details = details;
            return this;
        }
    }
}
