package com.example.demo.resolver;

import com.example.demo.bean.DeleteResponse;
import com.example.demo.model.PartnerProject;
import com.example.demo.model.PartnerProjectId;
import com.example.demo.model.Project;
import com.example.demo.repository.PartnerProjectRepository;
import com.example.demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class PartnerProjectResolver {
    private final PartnerProjectRepository projectRepository;

    @Autowired
    public PartnerProjectResolver(PartnerProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @QueryMapping
    public Iterable<PartnerProject> allPartnerProject() {
        return projectRepository.findAll();
    }

    @QueryMapping
    public PartnerProject partnerProject(@Argument PartnerProjectId id) {
        Optional<PartnerProject> project = projectRepository.findById(id);
        return project.orElse(null);
    }

    @MutationMapping
    public PartnerProject createPartnerProject(@Argument PartnerProject partnerProject) {
        return projectRepository.save(partnerProject);
    }

    @MutationMapping
    public PartnerProject updatePartnerProject(@Argument PartnerProject partnerProject) {
        return projectRepository.save(partnerProject);
    }

    @MutationMapping
    public DeleteResponse deletePartnerProject(@Argument PartnerProjectId id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return new DeleteResponse(true);
        }
        return new DeleteResponse(false, "Partner project not found");
    }
}
