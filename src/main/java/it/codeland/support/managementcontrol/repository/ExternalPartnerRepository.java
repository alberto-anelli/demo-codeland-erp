package it.codeland.support.managementcontrol.repository;

import it.codeland.support.managementcontrol.filter.ExternalPartnerFilter;
import it.codeland.support.managementcontrol.model.ExternalPartner;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface ExternalPartnerRepository extends JpaRepository<ExternalPartner, Long>, JpaSpecificationExecutor<ExternalPartner>, ManagementControlRepository<ExternalPartner> {

    default Specification<ExternalPartner> specification(ExternalPartnerFilter filter) {
//        ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matchingAny()
//                .withMatcher("companyName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
//                .withMatcher("referenceName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
//                .withMatcher("referenceSurname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
//                .withMatcher("mainSkills", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
//        Example<ExternalPartner> example = Example.of(ExternalPartner.match(filter.getKey()), ignoringExampleMatcher);
        return (root, query, builder) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.isNotEmpty(filter.getKey())) {
                predicates.add(builder.or(
                        builder.like(builder.lower(root.get("companyName")), "%" + filter.getKey().toLowerCase() + "%"),
                        builder.like(builder.lower(root.get("referenceName")), "%" + filter.getKey().toLowerCase() + "%"),
                        builder.like(builder.lower(root.get("referenceSurname")), "%" + filter.getKey().toLowerCase() + "%"),
                        builder.like(builder.lower(root.get("mainSkills")), "%" + filter.getKey().toLowerCase() + "%")));
            }
            if(filter.getActive() != null) {
                predicates.add(builder.lessThan(root.get("collaborationStartDate"), LocalDate.now()));
                predicates.add(builder.or(builder.greaterThan(root.get("collaborationEndDate"), LocalDate.now()),
                        builder.isNull(root.get("collaborationEndDate"))));
            }

//            predicates.add(QueryByExamplePredicateBuilder.getPredicate(root, builder, example));

            return builder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
