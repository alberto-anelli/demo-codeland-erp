package com.example.demo.resolver;

import com.example.demo.model.Collaborator;
import com.example.demo.repository.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class CollaboratorResolver {

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @QueryMapping
    public Collaborator collaborator(@Argument String idCollaborator) {
        Optional<Collaborator> collaborator = collaboratorRepository.findById(idCollaborator);
        return collaborator.orElse(null);
    }

    @QueryMapping
    public List<Collaborator> allCollaborators() {
        return collaboratorRepository.findAll();
    }

    @MutationMapping
    public Collaborator createCollaborator(@Argument String idCollaborator, @Argument String code, 
                                           @Argument String name, @Argument String surname, 
                                           @Argument String dateOfBirth, @Argument String hiringDate, 
                                           @Argument String address, @Argument String email, 
                                           @Argument String phone, @Argument String notes) {
        Collaborator collaborator = new Collaborator();
        collaborator.setCode(code);
        collaborator.setName(name);
        collaborator.setSurname(surname);
        collaborator.setDateOfBirth(LocalDateTime.parse(dateOfBirth)); 
        collaborator.setHiringDate(LocalDateTime.parse(hiringDate));
        collaborator.setAddress(address);
        collaborator.setEmail(email);
        collaborator.setPhone(phone);
        collaborator.setNotes(notes);

        return collaboratorRepository.save(collaborator);
    }

    @MutationMapping
    public Collaborator updateCollaborator(@Argument String idCollaborator, @Argument String code, 
                                           @Argument String name, @Argument String surname, 
                                           @Argument String dateOfBirth, @Argument String hiringDate, 
                                           @Argument String address, @Argument String email, 
                                           @Argument String phone, @Argument String notes) {
        Optional<Collaborator> optCollaborator = collaboratorRepository.findById(idCollaborator);
        if (optCollaborator.isPresent()) {
            Collaborator collaborator = optCollaborator.get();
            collaborator.setCode(code);
            collaborator.setName(name);
            collaborator.setSurname(surname);
            collaborator.setDateOfBirth(LocalDateTime.parse(dateOfBirth));
            collaborator.setHiringDate(LocalDateTime.parse(hiringDate));
            collaborator.setAddress(address);
            collaborator.setEmail(email);
            collaborator.setPhone(phone);
            collaborator.setNotes(notes);

            return collaboratorRepository.save(collaborator);
        } else {
            throw new RuntimeException("Collaborator not found");
        }
    }

    @MutationMapping
    public Boolean deleteCollaborator(@Argument String idCollaborator) {
        Optional<Collaborator> collaborator = collaboratorRepository.findById(idCollaborator);
        if (collaborator.isPresent()) {
            collaboratorRepository.delete(collaborator.get());
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
