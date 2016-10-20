package com.greenmist.persistence.mapper;

import com.greenmist.model.User;

/**
 * Created by eckob on 10/5/2016.
 */
public interface UserMapper {

    User getUserById(int id);

    User getUserByEmail(String email);

    boolean updateUser(User user);

    boolean insertUser(User user, String password);
}
