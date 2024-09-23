package it.codeland.support.managementcontrol.resolver;

import it.codeland.support.managementcontrol.data.ManagementControlPageData;
import it.codeland.support.managementcontrol.exception.EntityNotFoundException;
import it.codeland.support.managementcontrol.filter.GenericCostFilter;
import it.codeland.support.managementcontrol.model.GenericCost;
import it.codeland.support.managementcontrol.repository.GenericCostRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GenericCostResolver {

    private final GenericCostRepository repository;

    public GenericCostResolver(GenericCostRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public ManagementControlPageData<GenericCost> findGenericCosts(@Argument GenericCostFilter filter) {
        return ManagementControlPageData.fromPage(repository.findAll(repository.specification(filter),
                repository.pageable(filter)
        ));
    }

    @QueryMapping
    public GenericCost getGenericCost(@Argument Long id) {
        return repository.findById(id).orElse(null);
    }

    @MutationMapping
    public GenericCost updateGenericCost(@Argument GenericCost genericCost) {
        return repository.save(genericCost);
    }

    @MutationMapping
    public Boolean deleteGenericCost(@Argument Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("GenericCost (id:{0}) not found", id);
        }
    }

}
