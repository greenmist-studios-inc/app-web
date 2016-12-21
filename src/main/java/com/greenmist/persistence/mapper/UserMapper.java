package com.greenmist.persistence.mapper;

import com.greenmist.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by eckob on 10/5/2016.
 */
public interface UserMapper {

    User getUserById(long id);

    User getUserByEmail(String email);

    User updateUser(User user);

    void updateLastLogin(long id);

    void updatePassword(@Param("user") User user, @Param("password") String password);

    void insertUser(@Param("user") User user, @Param("password") String password);

    User getUserFromAuthToken(String authToken);
}
