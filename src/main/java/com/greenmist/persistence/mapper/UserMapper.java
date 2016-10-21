package com.greenmist.persistence.mapper;

import com.greenmist.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by eckob on 10/5/2016.
 */
public interface UserMapper {

    User getUserById(long id);

    User getUserByEmail(String email);

    boolean updateUser(@Param("User") User user);

    boolean insertUser(@Param("User") User user, @Param("password") String password);
}
