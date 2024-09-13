package com.example.demo.resolver;

import com.example.demo.model.Collaborator;
import com.example.demo.model.CollaboratorEconomics;
import com.example.demo.model.JobRole;
import com.example.demo.repository.CollaboratorEconomicsRepository;
import com.example.demo.repository.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CollaboratorEconomicsResolver {

    private final CollaboratorEconomicsRepository collaboratorEconomicsRepository;

    @Autowired
    public CollaboratorEconomicsResolver(CollaboratorEconomicsRepository collaboratorEconomicsRepository) {
        this.collaboratorEconomicsRepository = collaboratorEconomicsRepository;
    }

    // Resolver for getting a single CollaboratorEconomics by ID
    @QueryMapping
    public CollaboratorEconomics collaboratorEconomics(@Argument Long id) {
        return collaboratorEconomicsRepository.findById(id).orElse(null);
    }

    // Resolver for getting all CollaboratorEconomics
    @QueryMapping
    public List<CollaboratorEconomics> allCollaboratorEconomics() {
        return collaboratorEconomicsRepository.findAll();
    }

    // Mutation for creating a new CollaboratorEconomics
    @MutationMapping
    public CollaboratorEconomics createCollaboratorEconomics(
            @Argument CollaboratorEconomics collaboratorEconomics) {
        return collaboratorEconomicsRepository.save(collaboratorEconomics);
    }

    // Mutation for updating an existing CollaboratorEconomics
    @MutationMapping
    public CollaboratorEconomics updateCollaboratorEconomics(@Argument CollaboratorEconomics collaboratorEconomics) {
        return collaboratorEconomicsRepository.save(collaboratorEconomics);
    }

    // Mutation for deleting an existing CollaboratorEconomics
    @MutationMapping
    public Boolean deleteCollaboratorEconomics(@Argument Long idCollaboratorEconomics) {
        Optional<CollaboratorEconomics> collaboratorEconomics = collaboratorEconomicsRepository.findById(idCollaboratorEconomics);
        if (collaboratorEconomics.isPresent()) {
            collaboratorEconomicsRepository.delete(collaboratorEconomics.get());
            return true;
        }
        return false;
    }
}
