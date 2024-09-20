package it.codeland.support.managementcontrol.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "COLLABORATOR_ECONOMICS")
public class CollaboratorEconomics extends ErpAuditableEntity<Long> {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_collaborator_economics", length = 20, nullable = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_collaborator", nullable = false, insertable=false, updatable=false)
    private Collaborator collaborator;

    @Column(name = "id_collaborator")
    private Long idCollaborator;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "version_from_date", nullable = false)
    private LocalDate versionFromDate;

    @Column(name = "version_to_date")
    private LocalDate versionToDate;

    @Column(name = "hiring_date", nullable = false)
    private LocalDate hiringDate;

    @Column(name = "level", length = 1)
    private String level;

    @Column(name = "id_job_role", nullable = false)
    private Long idJobRole;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_job_role", referencedColumnName = "id_job_role", insertable = false, updatable = false)
    private JobRole jobRole;

    @Column(name = "leaving_date")
    private LocalDate leavingDate;

    @Column(name = "smart_working_days")
    private Integer smartWorkingDays;

    @Column(name = "ral_based", nullable = false)
    private Boolean ralBased;

    @Column(name = "ral")
    private Float ral;

    @Column(name = "month_add_on")
    private Float monthAddOn;

    @Column(name = "year_add_on")
    private Float yearAddOn;

    @Column(name = "ticket_restaurant")
    private Float ticketRestaurant;

    @Column(name = "award")
    private Float award;

}
