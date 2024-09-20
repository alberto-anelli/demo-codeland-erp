package com.example.demo.resolver;

import com.example.demo.bean.DeleteResponse;
import com.example.demo.model.ProjectGroup;
import com.example.demo.model.ProjectGroupYearEstimation;
import com.example.demo.model.ProjectGroupYearEstimationId;
import com.example.demo.repository.ProjectGroupRepository;
import com.example.demo.repository.ProjectGroupYearEstimationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class ProjectGroupYearEstimationResolver {
    private final ProjectGroupYearEstimationRepository projectGroupYearEstimationRepository;

    @Autowired
    public ProjectGroupYearEstimationResolver(ProjectGroupYearEstimationRepository projectGroupYearEstimationRepository) {
        this.projectGroupYearEstimationRepository = projectGroupYearEstimationRepository;
    }

    @QueryMapping
    public Iterable<ProjectGroupYearEstimation> allProjectGroupYearEstimation() {
        return projectGroupYearEstimationRepository.findAll();
    }

    @QueryMapping
    public ProjectGroupYearEstimation projectGroupYearEstimation(@Argument ProjectGroupYearEstimationId id) {
        Optional<ProjectGroupYearEstimation> projectGroup = projectGroupYearEstimationRepository.findById(id);
        return projectGroup.orElse(null);
    }

    @MutationMapping
    public ProjectGroupYearEstimation createProjectGroupYearEstimation(@Argument ProjectGroupYearEstimation projectGroupYearEstimation) {
        return projectGroupYearEstimationRepository.save(projectGroupYearEstimation);
    }

    @MutationMapping
    public ProjectGroupYearEstimation updateProjectGroupYearEstimation(@Argument ProjectGroupYearEstimation projectGroupYearEstimation) {
        return projectGroupYearEstimationRepository.save(projectGroupYearEstimation);
    }

    @MutationMapping
    public DeleteResponse deleteProjectGroupYearEstimation(@Argument ProjectGroupYearEstimationId id) {
        Optional<ProjectGroupYearEstimation> projectGroupYearEstimation = projectGroupYearEstimationRepository.findById(id);
        if (projectGroupYearEstimation.isPresent()) {
            projectGroupYearEstimationRepository.deleteById(id);
            return new DeleteResponse(true);
        } else {
            return new DeleteResponse(false, "Year Estimation not found");
        }
    }
}
