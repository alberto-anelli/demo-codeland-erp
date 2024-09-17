package com.example.demo.repository;

import com.example.demo.data.IEntityManager;
import com.example.demo.model.ProjectGroup;

import java.util.List;

public interface ErpProjectGroupRepository extends IEntityManager<ProjectGroup, Long> {
    List<ProjectGroup> findActiveProjectGroup();
}
