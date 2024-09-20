package it.codeland.support.managementcontrol.resolver;

import it.codeland.support.managementcontrol.exception.EntityNotFoundException;
import it.codeland.support.managementcontrol.exception.InvalidInputException;
import it.codeland.support.managementcontrol.model.CollaboratorEconomics;
import it.codeland.support.managementcontrol.repository.CollaboratorEconomicsRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class CollaboratorEconomicsResolver {
    private final CollaboratorEconomicsRepository repository;
    public CollaboratorEconomicsResolver(CollaboratorEconomicsRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public CollaboratorEconomics getActiveVersion(@Argument Long idCollaborator) {
        return repository.findByIdCollaboratorAndVersionToDateIsNull(idCollaborator).orElse(null);
    }

    @QueryMapping
    public Iterable<CollaboratorEconomics> getVersions(@Argument Long idCollaborator) {
        return repository.findByIdCollaborator(idCollaborator);
    }

    // Mutation for updating an existing CollaboratorEconomics
    @MutationMapping
    public CollaboratorEconomics updateVersion(@Argument CollaboratorEconomics collaboratorEconomics,
                                                             @Argument LocalDate versionDate) {
        if(versionDate != null) {
            Optional<CollaboratorEconomics> optional = repository.findByIdCollaboratorAndVersionToDateIsNull(collaboratorEconomics.getIdCollaborator());
            if(optional.isPresent()) {
                CollaboratorEconomics version = optional.get();
                version.setVersionToDate(versionDate);
                repository.save(version);
                collaboratorEconomics.setId(null);
                collaboratorEconomics.setVersionFromDate(versionDate);
                return repository.save(collaboratorEconomics);
            } else {
                throw new InvalidInputException("No version found for collaborator " + collaboratorEconomics.getIdCollaborator());
            }
        } else if(collaboratorEconomics.getId() != null) {
            return repository.save(collaboratorEconomics);
        }
        throw new InvalidInputException("Could not insert new collaborator economics without version date");
    }

    // Mutation for deleting an existing CollaboratorEconomics
    @MutationMapping
    public Boolean deleteVersion(@Argument Long id) {
        Optional<CollaboratorEconomics> collaboratorEconomics = repository.findById(id);
        if (collaboratorEconomics.isPresent()) {
            repository.delete(collaboratorEconomics.get());
            return true;
        }
        throw new EntityNotFoundException("Version (id:{0}) not found", id);
    }
}
