package com.greenmist.interceptor;

import com.greenmist.annotation.Authenticate;
import com.greenmist.annotation.ResetPassword;
import com.greenmist.exception.AuthorizationException;
import com.greenmist.exception.ErrorException;
import com.greenmist.exception.code.ErrorCode;
import com.greenmist.model.AuthToken;
import com.greenmist.model.ResetPasswordToken;
import com.greenmist.rest.response.ErrorResponse;
import com.greenmist.service.AuthTokenService;
import com.greenmist.service.ResetPasswordTokenService;
import com.greenmist.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import java.lang.reflect.Method;

import static com.greenmist.rest.Headers.ACCOUNT_HEADER;
import static com.greenmist.rest.Headers.RESET_PASSWORD_TOKEN_HEADER;
import static com.greenmist.rest.Headers.TOKEN_HEADER;

/**
 * Created by eckob on 12/21/2016.
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private AuthTokenService authTokenService;
    private ResetPasswordTokenService resetPasswordTokenService;

    @Autowired
    public AuthenticationInterceptor(AuthTokenService authTokenService, ResetPasswordTokenService resetPasswordTokenService) {
        this.authTokenService = authTokenService;
        this.resetPasswordTokenService = resetPasswordTokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod hm=(HandlerMethod)handler;
        Method method=hm.getMethod();

        if(method.getDeclaringClass().isAnnotationPresent(RestController.class)){
            if(method.isAnnotationPresent(Authenticate.class)) {
                authenticate(request);
            } else if(method.isAnnotationPresent(ResetPassword.class)) {
                authenticateWithResetPasswordToken(request);
            }
        }
        return true;
    }

    protected void authenticate(HttpServletRequest request) throws Exception {
        if (!hasAuthenticationHeadersSet(request, TOKEN_HEADER)) {
            throw new ErrorException(ErrorCode.MISSING_AUTHENTICATION_HEADERS, null);
        } else {
            try {
                String userId = request.getHeader(ACCOUNT_HEADER);
                String authToken = request.getHeader(TOKEN_HEADER);
                authTokenService.checkAuthToken(new AuthToken(Integer.parseInt(userId), authToken));
            } catch (NumberFormatException e) {
                throw new ErrorException(ErrorCode.INVALID_ACCOUNT_ID, "X-Account must be numerical.");
            }
        }
    }

    protected void authenticateWithResetPasswordToken(HttpServletRequest request) throws Exception {
        if (!hasAuthenticationHeadersSet(request, RESET_PASSWORD_TOKEN_HEADER)) {
            throw new ErrorException(ErrorCode.MISSING_AUTHENTICATION_HEADERS, null);
        } else {
            try {
                String userId = request.getHeader(ACCOUNT_HEADER);
                String resetPasswordToken = request.getHeader(RESET_PASSWORD_TOKEN_HEADER);
                resetPasswordTokenService.checkResetPasswordToken(new ResetPasswordToken(Integer.parseInt(userId), resetPasswordToken));
            } catch (NumberFormatException e) {
                throw new ErrorException(ErrorCode.INVALID_ACCOUNT_ID, "X-Account must be numerical.");
            }
        }
    }

    private boolean hasAuthenticationHeadersSet(HttpServletRequest request, String tokenHeader) {
        return StringUtils.isNotBlank(request.getHeader(tokenHeader))
                && StringUtils.isNotBlank(request.getHeader(ACCOUNT_HEADER));
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
