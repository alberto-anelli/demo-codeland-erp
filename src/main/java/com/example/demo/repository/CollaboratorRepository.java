package com.example.demo.repository;

import com.example.demo.model.Collaborator;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long> {

    @Query("SELECT c FROM Collaborator c JOIN CollaboratorEconomics ce ON c.id = ce.collaborator.id WHERE ce.level = :level AND ce.ral < :ral")
    List<Collaborator> findCollaboratorsByEconomicsCriteria(@Param("level") String level, @Param("ral") float ral);

}
