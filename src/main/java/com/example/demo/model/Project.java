package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "project")
public class Project extends ErpAuditableEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "notes")
    private String notes;

    @Column(name = "active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "id_project_group", nullable = false, insertable = false, updatable = false)
    private ProjectGroup projectGroup;

    @Column(name = "id_project_group")
    private Long idProjectGroup;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<RevenueStream> revenues;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<PartnerProject> partnerProjects;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<DayProjectTracking> dayProjectTrackings;

}
