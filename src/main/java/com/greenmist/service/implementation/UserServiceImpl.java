package com.greenmist.service.implementation;

import com.greenmist.model.User;
import com.greenmist.persistence.mapper.UserMapper;
import com.greenmist.service.UserService;
import com.greenmist.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by eckob on 10/6/2016.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        passwordEncoder = new BCryptPasswordEncoder();
        this.userMapper = userMapper;
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public boolean insertUser(User user, String password) {
        if (StringUtils.isNotBlank(user.getEmail()) && userMapper.getUserByEmail(user.getEmail()) != null) {
            return false;
        } else {
            String passwordHash = passwordEncoder.encode(password);
            userMapper.insertUser(user, passwordHash);
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }


}
