package com.example.demo.resolver;

import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.exception.InvalidInputException;
import com.example.demo.model.Collaborator;
import com.example.demo.model.CollaboratorEconomics;
import com.example.demo.model.JobRole;
import com.example.demo.repository.CollaboratorEconomicsRepository;
import com.example.demo.repository.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;
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
    public CollaboratorEconomics updateCollaboratorEconomics(@Argument CollaboratorEconomics collaboratorEconomics,
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
    public Boolean deleteCollaboratorEconomics(@Argument Long id) {
        Optional<CollaboratorEconomics> collaboratorEconomics = repository.findById(id);
        if (collaboratorEconomics.isPresent()) {
            repository.delete(collaboratorEconomics.get());
            return true;
        }
        throw new EntityNotFoundException("Version not found");
    }
}
