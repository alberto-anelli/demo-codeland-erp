package it.codeland.support.managementcontrol.data;

import it.codeland.support.managementcontrol.model.ErpEntity;
import it.codeland.support.managementcontrol.util.DataMapperUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractEntityManager<T extends ErpEntity<ID>, ID extends Serializable>
        implements IEntityManager<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public T patchEntity(ID id, T entityData) {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(entityData, "entityData");

        try {

            T entity = (T) entityManager.find(entityData.getClass(), id);

            if (!entity.getId().equals(id)) {
                throw new IllegalArgumentException("entity id does not match");
            }

            DataMapperUtils.patchProperties(entity, entityData);
            // entity = mapEntityDataTo(entityData, entity, EntityOperationType.UPDATE);
            entity = entityManager.merge(entity);
            return entity;
        } catch (RuntimeException ex) {
            // TODO throw proper exception
            throw ex;
        } catch (Exception ex) {
            // TODO throw proper exception
            throw new RuntimeException(ex);
        }
    }
}
