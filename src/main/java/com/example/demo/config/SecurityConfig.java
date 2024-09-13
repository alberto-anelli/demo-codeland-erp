package com.example.demo.config;

import org.opensaml.security.x509.X509Credential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.saml2.provider.service.registration.InMemoryRelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistration;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.registration.Saml2MessageBinding;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${spring.security.saml2.relyingparty.registration.saml-registration-id.identityprovider.entity-id}")
    private String entityId;

    @Value("${spring.security.saml2.relyingparty.registration.saml-registration-id.identityprovider.sso-url}")
    private String ssoUrl;

    @Value("${spring.security.saml2.relyingparty.registration.saml-registration-id.identityprovider.verification.credentials[0].certificate-location}")
    private String certificateLocation;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, RelyingPartyRegistrationRepository relyingPartyRegistrationRepository) throws Exception {
        http
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/login").authenticated() // Protect GraphQL endpoint
                .anyRequest().anonymous()  // Allow other requests
            )
            .saml2Login(saml2 -> saml2
                .relyingPartyRegistrationRepository(relyingPartyRegistrationRepository)  // Use automatically configured SAML repository
            )
            .csrf(csfr -> csfr.disable());  // Disable CSRF for simplicity
        
        return http.build();
    }

    @Bean
    public RelyingPartyRegistrationRepository relyingPartyRegistrationRepository() {
        RelyingPartyRegistration registration = RelyingPartyRegistration
        
            .withRegistrationId("saml-registration-id")
            .assertingPartyDetails(assertingParty -> assertingParty
                .entityId(entityId)
                .singleSignOnServiceLocation(ssoUrl)
                .singleSignOnServiceBinding(Saml2MessageBinding.POST)  // Assuming you use POST binding
                )
            .build();

        return new InMemoryRelyingPartyRegistrationRepository(registration);
    }


    
}

