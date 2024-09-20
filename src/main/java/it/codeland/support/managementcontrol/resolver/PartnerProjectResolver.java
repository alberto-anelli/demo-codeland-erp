package it.codeland.support.managementcontrol.resolver;

import it.codeland.support.managementcontrol.bean.DeleteResponse;
import it.codeland.support.managementcontrol.model.PartnerProject;
import it.codeland.support.managementcontrol.model.PartnerProjectId;
import it.codeland.support.managementcontrol.repository.PartnerProjectRepository;
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
