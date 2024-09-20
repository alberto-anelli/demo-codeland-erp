package com.example.demo.resolver;

import com.example.demo.data.ErpPageData;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.filter.CollaboratorFilter;
import com.example.demo.model.Collaborator;
import com.example.demo.repository.CollaboratorRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class CollaboratorResolver {

    private final CollaboratorRepository repository;

    public CollaboratorResolver(CollaboratorRepository repository) {
        this.repository = repository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    public Collaborator collaborator(@Argument Long id) {
        return repository.findById(id).orElse(null);
    }

    @QueryMapping
    public ErpPageData<Collaborator> collaborators(@Argument CollaboratorFilter filter) {
        return ErpPageData.fromPage(repository.findAll(repository.specification(filter),
                repository.pageable(filter)
        ));
    }

    @QueryMapping
    public Iterable<Collaborator> allCollaborators() {
        return repository.findAll();
    }

//    @MutationMapping
//    public Collaborator createCollaborator(@Argument Collaborator collaborator) {
//        return repository.save(collaborator);
//    }

    @MutationMapping
    public Collaborator updateCollaborator(@Argument Collaborator collaborator) {
        return repository.save(collaborator);
    }

    @MutationMapping
    public Boolean deleteCollaborator(@Argument Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("Collaborator not found");
        }
    }

}
