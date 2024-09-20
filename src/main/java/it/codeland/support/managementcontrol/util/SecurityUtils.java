package it.codeland.support.managementcontrol.util;

import it.codeland.support.managementcontrol.support.NamedOidcUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml2.provider.service.authentication.DefaultSaml2AuthenticatedPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;

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
            if (authentication.getPrincipal() instanceof Saml2AuthenticatedPrincipal springSecurityUser) {
                userName = springSecurityUser.getName();
            } else if (authentication.getPrincipal() instanceof String string) {
                userName = string;
            } else {
                logger.warn("getCurrentUserName - unexpected \"authentication\" type {}", authentication.getClass().getName());
            }
        }
        return userName;
    }
}
