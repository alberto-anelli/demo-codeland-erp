package com.example.demo.repository;

import com.example.demo.filter.CollaboratorFilter;
import com.example.demo.filter.ExternalPartnerFilter;
import com.example.demo.model.Collaborator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.CollaboratorEconomics;
import com.example.demo.model.ExternalPartner;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long>, JpaSpecificationExecutor<Collaborator>, ErpRepository<Collaborator> {
    default Specification<Collaborator> specification(CollaboratorFilter filter) {
        return (root, query, builder) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if(filter != null) {
                if(StringUtils.isNotEmpty(filter.getKey())) {
                    predicates.add(builder.or(
                            builder.like(builder.lower(root.get("code")), "%" + filter.getKey().toLowerCase() + "%"),
                            builder.like(builder.lower(root.get("name")), "%" + filter.getKey().toLowerCase() + "%"),
                            builder.like(builder.lower(root.get("surname")), "%" + filter.getKey().toLowerCase() + "%")));
                }
                if(filter.getIdCompany() != null) {
                    predicates.add(builder.equal(root.get("idCompany"), filter.getIdCompany()));
                }
                if(filter.getIdJobRole() != null) {
                    predicates.add(builder.equal(root.join("collaboratorEconomics").get("idJobRole"), filter.getIdJobRole()));
                }
                if(filter.getActive() != null) {
                    Join<CollaboratorEconomics, Collaborator> collaboratorEconomics = root.join("collaboratorEconomics");
                    predicates.add(builder.lessThan(collaboratorEconomics.get("hiringDate"), LocalDate.now()));
                    predicates.add(builder.or(builder.greaterThan(collaboratorEconomics.get("leavingDate"), LocalDate.now()),
                            builder.isNull(collaboratorEconomics.get("leavingDate"))));
                }
            }
            return builder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
