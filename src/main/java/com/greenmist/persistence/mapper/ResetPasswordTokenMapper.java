package com.greenmist.persistence.mapper;

import com.greenmist.model.ResetPasswordToken;

/**
 * Created by eckob on 10/5/2016.
 */
public interface ResetPasswordTokenMapper {

    boolean isValidResetPasswordToken(ResetPasswordToken authToken);

    void insertResetPasswordToken(ResetPasswordToken authToken);

    ResetPasswordToken getResetPasswordToken(ResetPasswordToken authToken);

    void deleteResetPasswordTokenByUserId(int id);

    void deleteExpiredResetPasswordTokens();
}
