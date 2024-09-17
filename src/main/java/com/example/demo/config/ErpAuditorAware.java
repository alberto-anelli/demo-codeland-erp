package com.example.demo.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class ErpAuditorAware implements AuditorAware<String> {

//  @Override
//  public String getCurrentAuditor() {
//
//    String userName = SecurityUtils.getCurrentUserName();
//    if (userName != null) {
//      return userName;
//    }
//    return JPConstants.ANONYMOUS_USER;
//  }

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("anonymous");
    }
}
