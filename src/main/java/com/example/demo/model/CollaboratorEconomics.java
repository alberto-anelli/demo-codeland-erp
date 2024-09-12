package com.example.demo.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "COLLABORATOR_ECONOMICS")
public class CollaboratorEconomics implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_collaborator_economics", length = 20, nullable = true)
    private Long idCollaboratorEconomics;

    @ManyToOne
    @JoinColumn(name = "id_collaborator", referencedColumnName = "id_collaborator", nullable = false)
    private Collaborator collaborator;

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

    // Getters and Setters
    public Long getIdCollaboratorEconomics() {
        return idCollaboratorEconomics;
    }

    public void setIdCollaboratorEconomics(Long idCollaboratorEconomics) {
        this.idCollaboratorEconomics = idCollaboratorEconomics;
    }

    public Collaborator getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    public JobRole getJobRole() {
        return jobRole;
    }

    public void setJobRole(JobRole jobRole) {
        this.jobRole = jobRole;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public float getRal() {
        return ral;
    }

    public void setRal(float ral) {
        this.ral = ral;
    }

    public float getTicket() {
        return ticket;
    }

    public void setTicket(float ticket) {
        this.ticket = ticket;
    }

    public float getFixedPrize() {
        return fixedPrize;
    }

    public void setFixedPrize(float fixedPrize) {
        this.fixedPrize = fixedPrize;
    }

    public float getExpectedExtraPrize() {
        return expectedExtraPrize;
    }

    public void setExpectedExtraPrize(float expectedExtraPrize) {
        this.expectedExtraPrize = expectedExtraPrize;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
