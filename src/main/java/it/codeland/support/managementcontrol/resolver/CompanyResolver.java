package it.codeland.support.managementcontrol.resolver;

import it.codeland.support.managementcontrol.exception.EntityNotFoundException;
import it.codeland.support.managementcontrol.model.Company;
import it.codeland.support.managementcontrol.repository.CompanyRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CompanyResolver {

    private final CompanyRepository repository;

    public CompanyResolver(CompanyRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public Company company(@Argument Long id) {
        return repository.findById(id).orElse(null);
    }

    @QueryMapping
    public Iterable<Company> allCompanies() {
        return repository.findAll();
    }

    @MutationMapping
    public Company updateCompany(@Argument Company company) {
        return repository.save(company);
    }

    @MutationMapping
    public Boolean deleteCompany(@Argument Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("Company (id:{0}) not found", id);
        }
    }

}
