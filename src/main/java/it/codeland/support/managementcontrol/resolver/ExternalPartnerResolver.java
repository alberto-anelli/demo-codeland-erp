package it.codeland.support.managementcontrol.resolver;

import it.codeland.support.managementcontrol.bean.DeleteResponse;
import it.codeland.support.managementcontrol.data.ErpPageData;
import it.codeland.support.managementcontrol.exception.EntityNotFoundException;
import it.codeland.support.managementcontrol.filter.ExternalPartnerFilter;
import it.codeland.support.managementcontrol.model.ExternalPartner;
import it.codeland.support.managementcontrol.repository.ExternalPartnerRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class ExternalPartnerResolver {
    private final ExternalPartnerRepository repository;

    public ExternalPartnerResolver(ExternalPartnerRepository externalPartnerRepository) {
        this.repository = externalPartnerRepository;
    }

    @QueryMapping
    public Iterable<ExternalPartner> allExternalPartner() {
        return repository.findAll();
    }

    @QueryMapping
    public ExternalPartner externalPartner(@Argument Long id) {
        Optional<ExternalPartner> project = repository.findById(id);
        return project.orElse(null);
    }

    @QueryMapping
    public ErpPageData<ExternalPartner> externalPartners(@Argument ExternalPartnerFilter filter) {
        return ErpPageData.fromPage(repository.findAll(repository.specification(filter),
                repository.pageable(filter)
        ));
    }

    @MutationMapping
    public ExternalPartner updateExternalPartner(@Argument ExternalPartner externalPartner) {
        return repository.save(externalPartner);
    }

    @MutationMapping
    public Boolean deleteExternalPartner(@Argument Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        throw new EntityNotFoundException("Partner project (id:{0}) not found", id);
    }
}
