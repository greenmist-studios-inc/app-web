package com.greenmist.controller;

import com.greenmist.exception.ErrorException;
import com.greenmist.model.AuthToken;
import com.greenmist.rest.request.LoginRequest;
import com.greenmist.rest.response.SessionResponse;
import com.greenmist.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.greenmist.rest.Headers.ACCOUNT_HEADER;
import static com.greenmist.rest.Headers.TOKEN_HEADER;

/**
 * Created by eckob on 10/2/2016.
 */
@RestController
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/user/session", method = RequestMethod.POST)
    public SessionResponse login(@RequestBody LoginRequest request) throws Exception {
        if (request == null) {
            throw ErrorException.EMPTY_REQUESTS;
        } else {
            return sessionService.login(request.getEmail(), request.getPassword());
        }
    }

    @RequestMapping(value = "/user/session", method = RequestMethod.GET)
    public AuthToken getSession(@RequestHeader(ACCOUNT_HEADER) int accountId, @RequestHeader(TOKEN_HEADER) String token) throws Exception {
        return sessionService.checkAuthToken(accountId, token);
    }

    @RequestMapping(value = "/user/session", method = RequestMethod.DELETE)
    public void logout(@RequestHeader(ACCOUNT_HEADER) int accountId, @RequestHeader(TOKEN_HEADER) String token) throws Exception {
        sessionService.logout(new AuthToken(accountId, token));
    }
}
