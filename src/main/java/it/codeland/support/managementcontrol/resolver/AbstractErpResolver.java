package com.example.demo.resolver;

import com.example.demo.model.ErpEntity;
import com.example.demo.repository.ErpRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.io.Serializable;

public class AbstractErpResolver<E extends ErpEntity<ID>, ID extends Serializable, R extends JpaRepository<E, ID>> implements ErpResolver<E, ID> {
    final R repository;
    public AbstractErpResolver(R repository) {
        this.repository = repository;
    }
    @Override
    public E read(ID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Iterable<E> findAll() {
        return repository.findAll();
    }
}
