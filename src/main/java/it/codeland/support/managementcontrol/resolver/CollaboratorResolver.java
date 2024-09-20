package it.codeland.support.managementcontrol.resolver;

import it.codeland.support.managementcontrol.data.ErpPageData;
import it.codeland.support.managementcontrol.exception.EntityNotFoundException;
import it.codeland.support.managementcontrol.filter.CollaboratorFilter;
import it.codeland.support.managementcontrol.model.Collaborator;
import it.codeland.support.managementcontrol.repository.CollaboratorRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class CollaboratorResolver {

    private final CollaboratorRepository repository;

    public CollaboratorResolver(CollaboratorRepository repository) {
        this.repository = repository;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    @Secured("ROLE_USER")
    public Iterable<Collaborator> allCollaborators() {
        return repository.findAll();
    }

    @MutationMapping
    @Secured("ROLE_ADMIN")
    public Collaborator updateCollaborator(@Argument Collaborator collaborator) {
        return repository.save(collaborator);
    }

    @MutationMapping
    public Boolean deleteCollaborator(@Argument Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("Collaborator (id:{0}) not found");
        }
    }

}
