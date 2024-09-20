package it.codeland.support.managementcontrol.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.saml2.provider.service.authentication.OpenSaml4AuthenticationProvider;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2Authentication;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpServletResponse;

import java.util.Objects;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Value("${codeland.management.control.role.prefix}")
    private String rolePrefix;

    @Value("${codeland.management.control.role.attribute.name}")
    private String roleAttributeName;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
            RelyingPartyRegistrationRepository relyingPartyRegistrationRepository) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated())
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint((request, response, authException) -> {
                            // Return 401 Unauthorized instead of redirecting to SAML login
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                        }))
                .saml2Login(saml2 -> saml2
                                .authenticationManager(new ProviderManager(getOpenSaml4AuthenticationProvider()))
                        .relyingPartyRegistrationRepository(relyingPartyRegistrationRepository) // Use automatically
                                                                                                // configured SAML
                                                                                                // repository
                ).csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    private @NotNull OpenSaml4AuthenticationProvider getOpenSaml4AuthenticationProvider() {
        OpenSaml4AuthenticationProvider authenticationProvider = new OpenSaml4AuthenticationProvider();
        authenticationProvider.setResponseAuthenticationConverter(responseToken -> {
            Saml2Authentication authentication = OpenSaml4AuthenticationProvider
                    .createDefaultResponseAuthenticationConverter()
                    .convert(responseToken);
            if(authentication != null && authentication.getPrincipal() instanceof Saml2AuthenticatedPrincipal principal) {
                return new Saml2Authentication(principal, authentication.getSaml2Response(),
                        Objects.requireNonNull(principal.getAttribute(roleAttributeName)).stream().map(
                            attribute -> new SimpleGrantedAuthority(attribute.toString())
                ).toList());
            }
            return authentication;
        });
        return authenticationProvider;
    }

}
