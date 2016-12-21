package com.greenmist.service;

import com.greenmist.exception.AuthorizationException;
import com.greenmist.exception.EmailTakenException;
import com.greenmist.exception.ErrorException;
import com.greenmist.exception.UserInvalidException;
import com.greenmist.model.AuthToken;
import com.greenmist.model.User;
import com.greenmist.persistence.mapper.UserMapper;
import com.greenmist.rest.response.SessionResponse;
import com.greenmist.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by eckob on 10/6/2016.
 */
@Service
public class UserService {

    public static final String EMAIL_EMPTY_ERROR_MESSAGE = "Email field is empty.";
    public static final String NO_USER_FOUND_ERROR_MESSAGE = "That user does not exist.";

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserMapper userMapper;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    public void insertUser(User user, String password) throws UserInvalidException, EmailTakenException {
        if (StringUtils.isBlank(user.getEmail())) {
            throw new UserInvalidException(EMAIL_EMPTY_ERROR_MESSAGE);
        } else if (StringUtils.isNotBlank(user.getEmail()) && userMapper.getUserByEmail(user.getEmail()) != null) {
            LOGGER.debug("Email %s is taken.", user.getEmail());
            throw new EmailTakenException(user.getEmail());
        } else {
            String passwordHash = passwordEncoder.encode(password);
            userMapper.insertUser(user, passwordHash);
        }
    }

    public void updateLastLogin(long id) {
        userMapper.updateLastLogin(id);
    }

    public void updatePassword(User user, String password) {
        String passwordHash = passwordEncoder.encode(password);
        userMapper.updatePassword(user, passwordHash);
    }

    public User updateUser(User user, String email, String firstName, String lastName) throws UserInvalidException {
        if (user == null) {
            throw new UserInvalidException(NO_USER_FOUND_ERROR_MESSAGE);
        } else {
            if (StringUtils.isNotBlank(email)) {
                user.setEmail(email);
            }
            if (StringUtils.isNotBlank(firstName)) {
                user.setFirstName(firstName);
            }
            if (StringUtils.isNotBlank(lastName)) {
                user.setLastName(lastName);
            }
            return userMapper.updateUser(user);
        }
    }

    public User getUserFromAuthToken(String authToken) throws AuthorizationException {
        if (authToken == null) {
            throw new AuthorizationException("Auth token is invalid.");
        } else {
            return userMapper.getUserFromAuthToken(authToken);
        }
    }
}
