package com.example.demo.repository;

import com.example.demo.model.ProjectGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectGroupRepository extends JpaRepository<ProjectGroup, Long> {
}
