package com.example.demo.resolver;

import com.example.demo.model.ProjectGroup;
import com.example.demo.repository.ProjectGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class ProjectGroupResolver {
    private final ProjectGroupRepository projectGroupRepository;

    @Autowired
    public ProjectGroupResolver(ProjectGroupRepository projectGroupRepository) {
        this.projectGroupRepository = projectGroupRepository;
    }

    @QueryMapping
    public Iterable<ProjectGroup> allProjectGroup() {
        return projectGroupRepository.findAll();
    }

    @QueryMapping
    public ProjectGroup projectGroup(@Argument Long idProjectGroup) {
        Optional<ProjectGroup> projectGroup = projectGroupRepository.findById(idProjectGroup);
        return projectGroup.orElse(null);
    }

    @MutationMapping
    public ProjectGroup createProjectGroup(@Argument ProjectGroup projectGroup) {
        return projectGroupRepository.save(projectGroup);
    }

    @MutationMapping
    public ProjectGroup updateProjectGroup(@Argument ProjectGroup projectGroup) {
        return projectGroupRepository.save(projectGroup);
    }

    @MutationMapping
    public Boolean deleteProjectGroup(@Argument Long idProjectGroup) {
        if (projectGroupRepository.existsById(idProjectGroup)) {
            projectGroupRepository.deleteById(idProjectGroup);
            return true;
        } else {
            throw new RuntimeException("Collaborator not found");
        }
    }
}
