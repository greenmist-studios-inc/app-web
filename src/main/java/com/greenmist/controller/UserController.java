package com.greenmist.controller;

import com.greenmist.model.User;
import com.greenmist.rest.request.RegisterUserRequest;
import com.greenmist.utils.converter.UserConverter;
import org.springframework.web.bind.annotation.*;

/**
 * Created by eckob on 10/2/2016.
 */
@RestController
public class UserController {

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public User greeting(@RequestBody RegisterUserRequest request) throws Exception {
        User user = UserConverter.to(request);

        return user;
    }
}
