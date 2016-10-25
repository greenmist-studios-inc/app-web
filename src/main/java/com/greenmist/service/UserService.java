package com.greenmist.service;

import com.greenmist.exception.EmailTakenException;
import com.greenmist.exception.ErrorException;
import com.greenmist.exception.UserInvalidException;
import com.greenmist.model.User;

/**
 * Created by eckob on 10/6/2016.
 */
public interface UserService {

    User getUserById(long id);

    User getUserByEmail(String email);

    boolean insertUser(User user, String password) throws EmailTakenException, UserInvalidException;

    boolean updateUser(User user);
}
