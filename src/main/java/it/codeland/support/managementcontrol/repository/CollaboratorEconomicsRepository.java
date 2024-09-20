package com.example.demo.repository;

import com.example.demo.model.CollaboratorEconomics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CollaboratorEconomicsRepository extends JpaRepository<CollaboratorEconomics, Long> {
    Iterable<CollaboratorEconomics> findByIdCollaborator(Long idCollaborator);
    Optional<CollaboratorEconomics> findByIdCollaboratorAndVersionToDateIsNull(Long idCollaborator);
}
