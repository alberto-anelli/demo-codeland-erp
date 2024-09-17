package com.example.demo.resolver;

import com.example.demo.bean.DeleteResponse;
import com.example.demo.data.ErpPageData;
import com.example.demo.model.ProjectGroup;
import com.example.demo.repository.ProjectGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    public Iterable<ProjectGroup> activeProjectGroup() {
        return projectGroupRepository.findProjectGroupByActive(Boolean.TRUE);
    }

    @QueryMapping
    public ProjectGroup projectGroup(@Argument Long id) {
        Optional<ProjectGroup> projectGroup = projectGroupRepository.findById(id);
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
    public ProjectGroup patchProjectGroup(@Argument ProjectGroup projectGroup) {
        return projectGroupRepository.patchEntity(projectGroup.getId(), projectGroup);
    }

    @MutationMapping
    public DeleteResponse deleteProjectGroup(@Argument Long id) {
        Optional<ProjectGroup> projectGroup = projectGroupRepository.findById(id);
        if (projectGroup.isPresent()) {
            projectGroupRepository.deleteById(id);
            return new DeleteResponse(true);
        }
        return new DeleteResponse(false, "Project not found");
    }
}
