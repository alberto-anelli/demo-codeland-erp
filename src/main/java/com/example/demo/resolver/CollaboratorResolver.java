package com.example.demo.resolver;

import com.example.demo.model.Collaborator;
import com.example.demo.repository.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CollaboratorResolver {

    private final CollaboratorRepository collaboratorRepository;

    @Autowired
    public CollaboratorResolver(CollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }

    @QueryMapping
    public Collaborator collaborator(@Argument Long id) {
        Optional<Collaborator> collaborator = collaboratorRepository.findById(id);
        return collaborator.orElse(null);
    }

    @QueryMapping
    public List<Collaborator> allCollaborators() {
        return collaboratorRepository.findAll();
    }

    @MutationMapping
    public Collaborator createCollaborator(@Argument Collaborator collaborator) {
        return collaboratorRepository.save(collaborator);
    }

    @MutationMapping
    public Collaborator updateCollaborator(@Argument Collaborator collaborator) {
        return collaboratorRepository.save(collaborator);
    }

    @MutationMapping
    public Boolean deleteCollaborator(@Argument Long id) {
        if (collaboratorRepository.existsById(id)) {
            collaboratorRepository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("Collaborator not found");
        }
    }

    @QueryMapping
    public List<Collaborator> collaboratorsByEconomicsCriteria(
        @Argument String level,
        @Argument Float ralBelow
    ) {
        return collaboratorRepository.findCollaboratorsByEconomicsCriteria(level, ralBelow);
    }

}
