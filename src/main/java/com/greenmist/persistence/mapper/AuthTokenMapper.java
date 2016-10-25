package com.greenmist.persistence.mapper;

import com.greenmist.model.AuthToken;
import com.greenmist.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by eckob on 10/5/2016.
 */
public interface TokenMapper {

    void updateAuthToken(AuthToken authToken);

    void insertAuthToken(AuthToken authToken);

    void deleteExpiredAuthTokens();
}
