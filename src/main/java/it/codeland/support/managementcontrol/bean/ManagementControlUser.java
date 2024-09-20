package it.codeland.support.managementcontrol.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@Setter
@Builder
public class ManagementControlUser {
    private String email;
    private List<GrantedAuthority> authorities;
    private String name;
    private String surname;
    private Long idCollaborator;
}
