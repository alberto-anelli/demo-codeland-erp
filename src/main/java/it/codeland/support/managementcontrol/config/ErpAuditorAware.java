package it.codeland.support.managementcontrol.config;

import it.codeland.support.managementcontrol.util.SecurityUtils;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class ErpAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String userName = SecurityUtils.getCurrentUserName();
        if (userName != null) {

            return Optional.of(userName);
        }
        return Optional.of("anonymous");
    }
}
