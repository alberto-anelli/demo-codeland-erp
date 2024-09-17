package com.example.demo.repository;

import com.example.demo.data.IEntityManager;
import com.example.demo.model.ProjectGroup;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface ProjectGroupRepository extends JpaRepository<ProjectGroup, Long>, ErpProjectGroupRepository {
    List<ProjectGroup> findProjectGroupByActive(Boolean active);
}
