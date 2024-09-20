package it.codeland.support.managementcontrol.resolver;

import it.codeland.support.managementcontrol.bean.ManagementControlUser;
import it.codeland.support.managementcontrol.model.Collaborator;
import it.codeland.support.managementcontrol.repository.CollaboratorRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Optional;

@Controller
public class IndexResolver {

    private final CollaboratorRepository collaboratorRepository;

    public IndexResolver(CollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }

    @GetMapping("/csrf")
    public String csrf(HttpServletResponse response, CsrfToken csrfToken) {
        if(csrfToken !=null) {
            response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
        }
        return null;
    }

    @GetMapping("/")
    public String index(Model model, Authentication user) {
        model.addAttribute("user", user);
        return "index";
    }

    @QueryMapping
    public ManagementControlUser whoami(Authentication user) {
        Optional<Collaborator> collaborator = collaboratorRepository.findByEmail(user.getName());
        return collaborator.map(value -> ManagementControlUser.builder()
                .email(user.getName())
                .name(value.getName())
                .surname(value.getSurname())
                .idCollaborator(value.getId())
                .build()).orElse(null);
    }

}

