package it.codeland.support.managementcontrol.repository;

import it.codeland.support.managementcontrol.model.ProjectGroupYearEstimation;
import it.codeland.support.managementcontrol.model.ProjectGroupYearEstimationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectGroupYearEstimationRepository extends JpaRepository<ProjectGroupYearEstimation, ProjectGroupYearEstimationId> {

}
