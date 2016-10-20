package com.greenmist.service;

import com.greenmist.model.User;

/**
 * Created by eckob on 10/6/2016.
 */
public interface UserService {

    User getUserById(long id);

    User getUserByEmail(String email);

    boolean insertUser(User user, String password);

    boolean updateUser(User user);
}
