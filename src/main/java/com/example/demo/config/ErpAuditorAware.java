package com.example.demo.config;

import com.example.demo.util.SecurityUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNullApi;

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
