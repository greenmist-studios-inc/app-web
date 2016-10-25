package com.greenmist.service.implementation;

import com.greenmist.exception.EmailTakenException;
import com.greenmist.exception.ErrorException;
import com.greenmist.exception.UserInvalidException;
import com.greenmist.exception.code.ErrorCode;
import com.greenmist.model.User;
import com.greenmist.persistence.mapper.UserMapper;
import com.greenmist.service.UserService;
import com.greenmist.utils.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by eckob on 10/6/2016.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    public static final String EMAIL_EMPTY_ERROR_MESSAGE = "Email field is empty.";

    private final UserMapper userMapper;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        passwordEncoder = new BCryptPasswordEncoder();
        this.userMapper = userMapper;
    }

    @Override
    public User getUserById(long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public boolean insertUser(User user, String password) throws EmailTakenException, UserInvalidException {
        if (StringUtils.isBlank(user.getEmail())) {
            throw new UserInvalidException(EMAIL_EMPTY_ERROR_MESSAGE);
        } else if (StringUtils.isNotBlank(user.getEmail()) && userMapper.getUserByEmail(user.getEmail()) != null) {
            throw new EmailTakenException(user.getEmail());
        } else {
            String passwordHash = passwordEncoder.encode(password);
            userMapper.insertUser(user, passwordHash);
        }
        return true;
    }

    @Override
    public boolean updateUser(@Param("User") User user) {
        return false;
    }


}
