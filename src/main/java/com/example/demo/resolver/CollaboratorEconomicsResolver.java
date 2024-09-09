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

    @Autowired
    private CollaboratorEconomicsRepository collaboratorEconomicsRepository;

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    // Resolver for getting a single CollaboratorEconomics by ID
    @QueryMapping
    public CollaboratorEconomics collaboratorEconomics(@Argument String id) {
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
            @Argument String idCollaborator,
            @Argument String jobRole,
            @Argument String level,
            @Argument Float ral,
            @Argument Float ticket,
            @Argument Float fixedPrize,
            @Argument Float expectedExtraPrize,
            @Argument String notes) {

        Collaborator collaborator = collaboratorRepository.findById(idCollaborator).orElseThrow(() -> new RuntimeException("Collaborator not found"));
        CollaboratorEconomics collaboratorEconomics = new CollaboratorEconomics(
                collaborator,
                JobRole.valueOf(jobRole),
                level,
                ral,
                ticket,
                fixedPrize,
                expectedExtraPrize,
                notes
        );

        return collaboratorEconomicsRepository.save(collaboratorEconomics);
    }

    // Mutation for updating an existing CollaboratorEconomics
    @MutationMapping
    public CollaboratorEconomics updateCollaboratorEconomics(
            @Argument String idCollaboratorEconomics,
            @Argument String idCollaborator,
            @Argument String jobRole,
            @Argument String level,
            @Argument Float ral,
            @Argument Float ticket,
            @Argument Float fixedPrize,
            @Argument Float expectedExtraPrize,
            @Argument String notes) {

        CollaboratorEconomics collaboratorEconomics = collaboratorEconomicsRepository.findById(idCollaboratorEconomics).orElseThrow(() -> new RuntimeException("CollaboratorEconomics not found"));

        if (idCollaborator != null) {
            Collaborator collaborator = collaboratorRepository.findById(idCollaborator).orElseThrow(() -> new RuntimeException("Collaborator not found"));
            collaboratorEconomics.setCollaborator(collaborator);
        }
        if (jobRole != null) {
            collaboratorEconomics.setJobRole(JobRole.valueOf(jobRole));
        }
        if (level != null) {
            collaboratorEconomics.setLevel(level);
        }
        if (ral != null) {
            collaboratorEconomics.setRal(ral);
        }
        if (ticket != null) {
            collaboratorEconomics.setTicket(ticket);
        }
        if (fixedPrize != null) {
            collaboratorEconomics.setFixedPrize(fixedPrize);
        }
        if (expectedExtraPrize != null) {
            collaboratorEconomics.setExpectedExtraPrize(expectedExtraPrize);
        }
        if (notes != null) {
            collaboratorEconomics.setNotes(notes);
        }

        return collaboratorEconomicsRepository.save(collaboratorEconomics);
    }

    // Mutation for deleting an existing CollaboratorEconomics
    @MutationMapping
    public Boolean deleteCollaboratorEconomics(@Argument String idCollaboratorEconomics) {
        Optional<CollaboratorEconomics> collaboratorEconomics = collaboratorEconomicsRepository.findById(idCollaboratorEconomics);
        if (collaboratorEconomics.isPresent()) {
            collaboratorEconomicsRepository.delete(collaboratorEconomics.get());
            return true;
        }
        return false;
    }
}
