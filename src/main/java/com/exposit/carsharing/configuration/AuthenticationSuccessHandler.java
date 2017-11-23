package com.exposit.carsharing.configuration;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handles successful authentication and responses for a successful
 * authentication 200 without redirect logic.
 */
@Setter
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private RequestCache requestCache;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws ServletException, IOException {

        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (requestCache.getRequest(request, response) == null) {
            return;
        }

        if (hasTargetUrlParameter(request)) {
            requestCache.removeRequest(request, response);
        }
    }

    private boolean hasTargetUrlParameter(HttpServletRequest request) {
        String targetUrlParam = getTargetUrlParameter();
        return isAlwaysUseDefaultTargetUrl() || StringUtils.hasText(request.getParameter(targetUrlParam));
    }
}