package it.codeland.support.managementcontrol.repository;

import it.codeland.support.managementcontrol.model.PartnerProject;
import it.codeland.support.managementcontrol.model.PartnerProjectId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerProjectRepository extends JpaRepository<PartnerProject, PartnerProjectId> {
}
