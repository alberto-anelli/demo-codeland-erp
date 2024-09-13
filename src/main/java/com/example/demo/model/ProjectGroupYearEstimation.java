package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "project_group_year_estimation")
@IdClass(ProjectGroupYearEstimationId.class)
public class ProjectGroupYearEstimation {

    @Id
    @Column(name = "year")
    private int year;

    @Column(name = "turnover")
    private Float turnover;

    @Column(name = "ebit")
    private Float ebit;

    @Column(name = "notes", length = 250)
    private String notes;

    @ManyToOne
    @JoinColumn(name = "id_project_group", nullable = false, insertable = false, updatable = false)
    private ProjectGroup projectGroup;

    @Id
    @Column(name = "id_project_group")
    private Long idProjectGroup;
}
