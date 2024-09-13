package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "day_project_tracking")
@IdClass(DayProjectTrackingId.class)
public class DayProjectTracking {
    @Id
    @Column(name = "id_project")
    private Long idProject;

    @ManyToOne
    @JoinColumn(name = "id_project", nullable = false, insertable = false, updatable = false)
    private Project project;

    @Id
    @Column(name = "id_collaborator")
    private Long idCollaborator;

    @ManyToOne
    @JoinColumn(name = "id_collaborator", nullable = false, insertable = false, updatable = false)
    private Collaborator collaborator;

    @Id
    @Column(name = "day")
    private LocalDate day;

    @Column(name = "hours")
    private int hours;

    @Column(name = "extra_no_night")
    private int extraNoNight;

    @Column(name = "extra_night")
    private int extraNight;

    @Column(name = "availability")
    private int availability;

    @Column(name = "moreInfo", length = 100)
    private String moreInfo;

    @Column(name = "notes", length = 250)
    private String notes;
}
