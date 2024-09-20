package it.codeland.support.managementcontrol.resolver;

import it.codeland.support.managementcontrol.bean.DeleteResponse;
import it.codeland.support.managementcontrol.data.ErpPageData;
import it.codeland.support.managementcontrol.filter.ExternalPartnerFilter;
import it.codeland.support.managementcontrol.model.ExternalPartner;
import it.codeland.support.managementcontrol.repository.ExternalPartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class ExternalPartnerResolver {
    private final ExternalPartnerRepository externalPartnerRepository;

    @Autowired
    public ExternalPartnerResolver(ExternalPartnerRepository externalPartnerRepository) {
        this.externalPartnerRepository = externalPartnerRepository;
    }

    @QueryMapping
    public Iterable<ExternalPartner> allExternalPartner() {
        return externalPartnerRepository.findAll();
    }

    @QueryMapping
    public ExternalPartner externalPartner(@Argument Long id) {
        Optional<ExternalPartner> project = externalPartnerRepository.findById(id);
        return project.orElse(null);
    }

    @QueryMapping
    public ErpPageData<ExternalPartner> externalPartners(@Argument ExternalPartnerFilter filter) {
        return ErpPageData.fromPage(externalPartnerRepository.findAll(externalPartnerRepository.specification(filter),
                externalPartnerRepository.pageable(filter)
        ));
    }

    @MutationMapping
    public ExternalPartner createExternalPartner(@Argument ExternalPartner externalPartner) {
        return externalPartnerRepository.save(externalPartner);
    }

    @MutationMapping
    public ExternalPartner updateExternalPartner(@Argument ExternalPartner externalPartner) {
        return externalPartnerRepository.save(externalPartner);
    }

    @MutationMapping
    public DeleteResponse deleteExternalPartner(@Argument Long id) {
        if (externalPartnerRepository.existsById(id)) {
            externalPartnerRepository.deleteById(id);
            return new DeleteResponse(true);
        }
        return new DeleteResponse(false, "Partner project not found");
    }
}
