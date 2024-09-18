package com.example.demo.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Baeldung
 *
 */
@Setter
@Getter
@ConfigurationProperties(prefix="codeland.jwt.authorization")
public class JwtAuthorizationProperties {

    /**
     * -- GETTER --
     *
     *
     * -- SETTER --
     *
     @return the groupsClaim
      * @param groupsClaim the groupsClaim to set
     */
    // Claim that has the group list
    private String groupsClaim = "groups";

    /**
     * -- GETTER --
     *
     *
     * -- SETTER --
     *
     @return the authoritiesPrefix
      * @param authoritiesPrefix the authoritiesPrefix to set
     */
    private String authoritiesPrefix = "ROLE_";

    /**
     * -- GETTER --
     *
     *
     * -- SETTER --
     *
     @return the groupToAuthorities
      * @param groupToAuthorities the groupToAuthorities to set
     */
    // map groupIds to a list of authorities.
    private Map<String,List<String>> groupToAuthorities = new HashMap<>();

}
