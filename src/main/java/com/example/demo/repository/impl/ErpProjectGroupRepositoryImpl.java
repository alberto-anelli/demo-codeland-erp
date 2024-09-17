package com.example.demo.repository.impl;

import com.example.demo.data.AbstractEntityManager;
import com.example.demo.model.ProjectGroup;
import com.example.demo.repository.ErpProjectGroupRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ErpProjectGroupRepositoryImpl extends AbstractEntityManager<ProjectGroup, Long> implements ErpProjectGroupRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProjectGroup> findActiveProjectGroup() {
        TypedQuery<ProjectGroup> query = entityManager.createQuery("SELECT u FROM ProjectGroup u WHERE u.active = true", ProjectGroup.class);
        return query.getResultList();
    }
}
