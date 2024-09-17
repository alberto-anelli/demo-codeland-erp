package com.example.demo.resolver;

import com.example.demo.bean.DeleteResponse;
import com.example.demo.model.ExternalPartner;
import com.example.demo.repository.ExternalPartnerRepository;
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
