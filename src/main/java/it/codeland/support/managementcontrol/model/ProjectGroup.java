package it.codeland.support.managementcontrol.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "project_group")
public class ProjectGroup extends ErpAuditableEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project_group", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "projectGroup")
    private List<Project> projects;

    @OneToMany(mappedBy = "projectGroup")
    private List<ProjectGroupYearEstimation> estimations;

    @OneToMany(mappedBy = "projectGroup")
    private List<ProjectGroupGenericCost> costs;
}
