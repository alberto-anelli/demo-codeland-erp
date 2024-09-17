package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "project_group_year_estimation")
public class ProjectGroupYearEstimation extends ErpAuditableEntity<ProjectGroupYearEstimationId> {

    @EmbeddedId
    private ProjectGroupYearEstimationId id;

    @Column(name = "turnover")
    private Float turnover;

    @Column(name = "ebit")
    private Float ebit;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "id_project_group", nullable = false, insertable = false, updatable = false)
    private ProjectGroup projectGroup;

}
