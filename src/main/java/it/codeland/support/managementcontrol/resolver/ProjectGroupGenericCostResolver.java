package it.codeland.support.managementcontrol.resolver;

import it.codeland.support.managementcontrol.bean.DeleteResponse;
import it.codeland.support.managementcontrol.model.ProjectGroupGenericCost;
import it.codeland.support.managementcontrol.repository.ProjectGroupGenericCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class ProjectGroupGenericCostResolver {
    private final ProjectGroupGenericCostRepository projectGroupGenericCostRepository;

    @Autowired
    public ProjectGroupGenericCostResolver(ProjectGroupGenericCostRepository projectGroupGenericCostRepository) {
        this.projectGroupGenericCostRepository = projectGroupGenericCostRepository;
    }

    @QueryMapping
    public Iterable<ProjectGroupGenericCost> allProjectGroupGenericCost() {
        return projectGroupGenericCostRepository.findAll();
    }

    @QueryMapping
    public ProjectGroupGenericCost projectGroupGenericCost(@Argument String id) {
        Optional<ProjectGroupGenericCost> project = projectGroupGenericCostRepository.findById(id);
        return project.orElse(null);
    }

    @MutationMapping
    public ProjectGroupGenericCost createProjectGroupGenericCost(@Argument ProjectGroupGenericCost projectGroupGenericCost) {
        return projectGroupGenericCostRepository.save(projectGroupGenericCost);
    }

    @MutationMapping
    public ProjectGroupGenericCost updateProjectGroupGenericCost(@Argument ProjectGroupGenericCost projectGroupGenericCost) {
        return projectGroupGenericCostRepository.save(projectGroupGenericCost);
    }

    @MutationMapping
    public DeleteResponse deleteProjectGroupGenericCost(@Argument String id) {
        Optional<ProjectGroupGenericCost> project = projectGroupGenericCostRepository.findById(id);
        if (project.isPresent()) {
            projectGroupGenericCostRepository.deleteById(id);
            return new DeleteResponse(true);
        }
        return new DeleteResponse(false, "ProjectGroupGenericCost not found");
    }
}
