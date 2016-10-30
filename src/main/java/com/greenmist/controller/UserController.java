package com.greenmist.controller;

import com.greenmist.annotation.Authenticate;
import com.greenmist.model.User;
import com.greenmist.rest.Headers;
import com.greenmist.rest.request.RegisterUserRequest;
import com.greenmist.rest.request.SetPasswordRequest;
import com.greenmist.rest.request.UpdateUserRequest;
import com.greenmist.rest.response.SessionResponse;
import com.greenmist.service.AccountService;
import com.greenmist.utils.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by eckob on 10/2/2016.
 */
@RestController
public class UserController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public SessionResponse registerUser(@RequestBody RegisterUserRequest request) throws Exception {
        User user = UserConverter.to(request);
        return accountService.createUser(user, request.getPassword());
    }

    @Authenticate
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public User updateUser(@RequestHeader(Headers.TOKEN_HEADER) String token, @RequestBody UpdateUserRequest request) throws Exception {
        return accountService.updateUser(token, request.getEmail(), request.getFirstName(), request.getLastName());
    }

    @Authenticate
    @RequestMapping(value = "/user/password", method = RequestMethod.POST)
    public void updateUserPassword(@RequestHeader(Headers.TOKEN_HEADER) String token, @RequestBody SetPasswordRequest request) throws Exception {
        accountService.updatePassword(token, request.getPassword());
    }
}
