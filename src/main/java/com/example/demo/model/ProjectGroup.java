package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "project_group")
public class ProjectGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project_group", nullable = false)
    private Long idProjectGroup;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "notes", nullable = false, length = 250)
    private String notes;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "projectGroup")
    private List<Project> projects;

    @OneToMany(mappedBy = "projectGroup")
    private List<ProjectGroupYearEstimation> estimations;

}
