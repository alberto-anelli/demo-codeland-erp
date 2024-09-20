package com.example.demo.resolver;

import com.example.demo.model.JobRole;
import com.example.demo.repository.JobRoleRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class JobRoleResolver {

    private final JobRoleRepository repository;

    public JobRoleResolver(JobRoleRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public JobRole jobRole(@Argument Long id) {
        return repository.findById(id).orElse(null);
    }

    @QueryMapping
    public Iterable<JobRole> allJobRoles() {
        return repository.findAll();
    }

    @MutationMapping
    public JobRole createJobRole(@Argument JobRole jobRole) {
        return repository.save(jobRole);
    }

    @MutationMapping
    public JobRole updateJobRole(@Argument JobRole jobRole) {
        return repository.save(jobRole);
    }

    @MutationMapping
    public Boolean deleteJobRole(@Argument Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("JobRole not found");
        }
    }

}
