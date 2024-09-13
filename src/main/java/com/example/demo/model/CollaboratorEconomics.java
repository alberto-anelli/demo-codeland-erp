package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "COLLABORATOR_ECONOMICS")
public class CollaboratorEconomics implements Serializable {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_collaborator_economics", length = 20, nullable = true)
    private Long idCollaboratorEconomics;

    @ManyToOne
    @JoinColumn(name = "id_collaborator", nullable = false, insertable=false, updatable=false)
    private Collaborator collaborator;

    @Column(name = "id_collaborator")
    private Long idCollaborator;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_role", nullable = false)
    private JobRole jobRole;

    @Column(name = "level", length = 1)
    private String level;

    @Column(name = "ral", nullable = false)
    private float ral;

    @Column(name = "ticket", nullable = false)
    private float ticket;

    @Column(name = "fixed_prize", nullable = false)
    private float fixedPrize;

    @Column(name = "expected_extra_prize", nullable = false)
    private float expectedExtraPrize;

    @Column(name = "notes", length = 250)
    private String notes;

    public CollaboratorEconomics(){}

    public CollaboratorEconomics(Collaborator collaborator, JobRole jobRole,
            String level, float ral, float ticket, float fixedPrize, float expectedExtraPrize, String notes) {
        this.collaborator = collaborator;
        this.jobRole = jobRole;
        this.level = level;
        this.ral = ral;
        this.ticket = ticket;
        this.fixedPrize = fixedPrize;
        this.expectedExtraPrize = expectedExtraPrize;
        this.notes = notes;
    }

}
