package com.greenmist.filter;

import com.greenmist.annotation.Authenticate;
import com.greenmist.exception.AuthorizationException;
import com.greenmist.exception.code.ErrorCode;
import com.greenmist.model.AuthToken;
import com.greenmist.rest.response.ErrorResponse;
import com.greenmist.service.AuthTokenService;
import com.greenmist.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

import static com.greenmist.rest.Headers.ACCOUNT_HEADER;
import static com.greenmist.rest.Headers.TOKEN_HEADER;

/**
 * Created by eckob on 10/29/2016.
 */
@Component
@Authenticate
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    private final AuthTokenService authTokenService;

    @Autowired
    public AuthenticationFilter(AuthTokenService authTokenService) {
        this.authTokenService = authTokenService;
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        if(!hasAuthenticationHeadersSet(containerRequestContext)) {
            containerRequestContext.abortWith(ErrorResponse
                    .badRequest(ErrorCode.MISSING_AUTHENTICATION_HEADERS)
                    .details(new String[0])
                    .build());
        } else {
            try {
                String userId = containerRequestContext.getHeaderString(ACCOUNT_HEADER);
                String authToken = containerRequestContext.getHeaderString(TOKEN_HEADER);
                authTokenService.checkAuthToken(new AuthToken(Integer.parseInt(userId), authToken));
            } catch (AuthorizationException e) {
                containerRequestContext.abortWith(ErrorResponse
                        .unauthorized(ErrorCode.UNAUTHORIZED)
                        .details(new String[0])
                        .build());
            } catch (NumberFormatException e) {
                containerRequestContext.abortWith(ErrorResponse
                        .badRequest(ErrorCode.INVALID_ACCOUNT_ID)
                        .details("X-Account must be numerical.")
                        .build());
            }
        }
    }

    private boolean hasAuthenticationHeadersSet(ContainerRequestContext containerRequestContext) {
        return containerRequestContext.getHeaders().containsKey(TOKEN_HEADER)
                && containerRequestContext.getHeaders().containsKey(ACCOUNT_HEADER)
                && StringUtils.isNotBlank(containerRequestContext.getHeaderString(TOKEN_HEADER))
                && StringUtils.isNotBlank(containerRequestContext.getHeaderString(ACCOUNT_HEADER));
    }
}
