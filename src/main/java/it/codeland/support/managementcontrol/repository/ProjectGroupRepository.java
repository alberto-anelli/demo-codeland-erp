package com.example.demo.repository;

import com.example.demo.data.IEntityManager;
import com.example.demo.model.ProjectGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectGroupRepository extends JpaRepository<ProjectGroup, Long>, IEntityManager<ProjectGroup, Long> {
    List<ProjectGroup> findProjectGroupByActive(Boolean active);
}
