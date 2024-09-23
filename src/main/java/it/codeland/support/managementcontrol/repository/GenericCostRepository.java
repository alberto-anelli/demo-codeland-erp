package it.codeland.support.managementcontrol.repository;

import it.codeland.support.managementcontrol.filter.CollaboratorFilter;
import it.codeland.support.managementcontrol.filter.GenericCostFilter;
import it.codeland.support.managementcontrol.model.Collaborator;
import it.codeland.support.managementcontrol.model.CollaboratorEconomics;
import it.codeland.support.managementcontrol.model.GenericCost;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface GenericCostRepository extends JpaRepository<GenericCost, Long>, JpaSpecificationExecutor<GenericCost>, ManagementControlRepository<GenericCost> {
    default Specification<GenericCost> specification(GenericCostFilter filter) {
        return (root, query, builder) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if(filter == null) {
                return builder.and(predicates.toArray(Predicate[]::new));
            }
            if(StringUtils.isNotEmpty(filter.getKey())) {
                predicates.add(builder.or(
                        builder.like(builder.lower(root.get("description")), "%" + filter.getKey().toLowerCase() + "%")));
            }
            if(filter.getIdArea() != null) {
                predicates.add(builder.equal(root.get("idArea"), filter.getIdArea()));
            }
            if(filter.getYear() != null) {
                predicates.add(builder.equal(root.get("year"), filter.getYear()));
            }

            return builder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
