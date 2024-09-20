package it.codeland.support.managementcontrol.resolver;

import it.codeland.support.managementcontrol.model.ErpEntity;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.io.Serializable;

public interface ErpResolver<E extends ErpEntity<ID>, ID extends Serializable> {
    @QueryMapping
    E read(@Argument ID id);

    @QueryMapping
    Iterable<E> findAll();
}
