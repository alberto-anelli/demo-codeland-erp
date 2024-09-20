package com.example.demo.repository;

import com.example.demo.model.ProjectGroupYearEstimation;
import com.example.demo.model.ProjectGroupYearEstimationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectGroupYearEstimationRepository extends JpaRepository<ProjectGroupYearEstimation, ProjectGroupYearEstimationId> {

}
