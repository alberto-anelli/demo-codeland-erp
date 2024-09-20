package it.codeland.support.managementcontrol.resolver;

import it.codeland.support.managementcontrol.bean.DeleteResponse;
import it.codeland.support.managementcontrol.model.ProjectGroup;
import it.codeland.support.managementcontrol.repository.ProjectGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Secured("ROLE_ADMIN")
    public Iterable<ProjectGroup> allProjectGroup() {
        return projectGroupRepository.findAll();
    }

    @QueryMapping
    public Iterable<ProjectGroup> activeProjectGroup() {
        return projectGroupRepository.findProjectGroupByActive(Boolean.TRUE);
    }

    @QueryMapping
    @Secured("SCOPE_User.Read")
    public ProjectGroup projectGroup(@Argument Long id) {
        SecurityContextHolder.getContext().getAuthentication();
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
