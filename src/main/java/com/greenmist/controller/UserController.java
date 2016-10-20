package com.greenmist.controller;

import com.greenmist.model.User;
import com.greenmist.rest.request.RegisterUserRequest;
import com.greenmist.service.UserService;
import com.greenmist.service.implementation.UserServiceImpl;
import com.greenmist.utils.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * Created by eckob on 10/2/2016.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public User greeting(@RequestBody RegisterUserRequest request) throws Exception {
        User user = UserConverter.to(request);
        userService.insertUser(user, request.getPassword());
        return user;
    }
}
