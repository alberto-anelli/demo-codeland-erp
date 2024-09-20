package it.codeland.support.managementcontrol.repository;

import it.codeland.support.managementcontrol.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
