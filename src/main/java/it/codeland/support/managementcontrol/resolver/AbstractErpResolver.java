package it.codeland.support.managementcontrol.resolver;

import it.codeland.support.managementcontrol.model.ErpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

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
