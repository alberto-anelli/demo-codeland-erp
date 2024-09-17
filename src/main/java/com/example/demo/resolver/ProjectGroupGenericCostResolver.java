package com.example.demo.resolver;

import com.example.demo.bean.DeleteResponse;
import com.example.demo.model.Project;
import com.example.demo.model.ProjectGroup;
import com.example.demo.repository.ProjectGroupRepository;
import com.example.demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class ProjectResolver {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectResolver(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @QueryMapping
    public Iterable<Project> allProject() {
        return projectRepository.findAll();
    }

    @QueryMapping
    public Project project(@Argument Long id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.orElse(null);
    }

    @MutationMapping
    public Project createProject(@Argument Project project) {
        return projectRepository.save(project);
    }

    @MutationMapping
    public Project updateProject(@Argument Project project) {
        return projectRepository.save(project);
    }

    @MutationMapping
    public DeleteResponse deleteProject(@Argument Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            projectRepository.deleteById(id);
            return new DeleteResponse(true);
        }
        return new DeleteResponse(false, "Project not found");
    }
}
