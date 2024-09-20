package it.codeland.support.managementcontrol.data;

import it.codeland.support.managementcontrol.model.ErpEntity;

import java.io.Serializable;

public interface IEntityManager<T extends ErpEntity<ID>, ID extends Serializable> {
    T patchEntity(ID id, T entity);
}
