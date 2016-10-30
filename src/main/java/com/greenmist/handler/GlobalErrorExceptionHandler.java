package com.greenmist.handler;

import com.greenmist.exception.ErrorException;
import com.greenmist.rest.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by eckob on 10/23/2016.
 */
@EnableWebMvc
@ControllerAdvice
public class GlobalErrorExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalErrorExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleErrorExceptionRequest(Exception e, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if (e instanceof ErrorException) {
            ErrorException errorException = (ErrorException) e;
            ErrorResponse errorResponse = new ErrorResponse(errorException);

            LOGGER.debug(errorResponse.toString());
            return handleExceptionInternal(e, errorResponse, headers, errorException.getErrorCode().getHttpStatus(), request);
        } else {
            LOGGER.debug(e.toString());
            return handleExceptionInternal(e, e, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
        }
    }
}