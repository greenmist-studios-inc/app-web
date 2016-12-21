package com.greenmist.task;

import com.greenmist.service.AuthTokenService;
import com.greenmist.service.ResetPasswordTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by eckob on 10/30/2016.
 */
@Component
public class ScheduledTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTests.class);

    private AuthTokenService authTokenService;
    private ResetPasswordTokenService resetPasswordTokenService;

    @Autowired
    public ScheduledTests(AuthTokenService authTokenService, ResetPasswordTokenService resetPasswordTokenService) {
        this.authTokenService = authTokenService;
        this.resetPasswordTokenService = resetPasswordTokenService;
    }

    @Scheduled(fixedRate = 3600000)
    public void reportCurrentTime() {
        LOGGER.info("Deleting expired auth tokens");
        authTokenService.deleteExpiredAuthTokens();

        LOGGER.info("Deleting expired reset password tokens");
        resetPasswordTokenService.deleteExpiredResetPasswordTokens();
    }
}
