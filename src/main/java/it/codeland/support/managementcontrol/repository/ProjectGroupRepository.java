package it.codeland.support.managementcontrol.repository;

import it.codeland.support.managementcontrol.data.IEntityManager;
import it.codeland.support.managementcontrol.model.ProjectGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectGroupRepository extends JpaRepository<ProjectGroup, Long>, IEntityManager<ProjectGroup, Long> {
    List<ProjectGroup> findProjectGroupByActive(Boolean active);
}
