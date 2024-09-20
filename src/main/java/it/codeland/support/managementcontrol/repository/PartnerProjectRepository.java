package com.example.demo.repository;

import com.example.demo.model.PartnerProject;
import com.example.demo.model.PartnerProjectId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerProjectRepository extends JpaRepository<PartnerProject, PartnerProjectId> {
}
