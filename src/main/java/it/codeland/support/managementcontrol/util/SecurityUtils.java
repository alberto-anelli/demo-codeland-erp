package com.example.demo.util;

import com.example.demo.support.NamedOidcUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public final class SecurityUtils {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtils.class);

    private SecurityUtils() {}

    public static Authentication getCurrentAuthentication() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication();
    }

    public static String getCurrentUserName() {
        Authentication authentication = getCurrentAuthentication();
        String userName = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof NamedOidcUser springSecurityUser) {
                userName = springSecurityUser.getPreferredUsername();
            } else if (authentication.getPrincipal() instanceof String string) {
                userName = string;
            } else {
                logger.warn("getCurrentUserName - unexpected \"authentication\" type {}", authentication.getClass().getName());
            }
        }
        return userName;
    }
}
