package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "project_group_generic_cost")
public class ProjectGroupGenericCost {
    @Id
    @Column(name = "month", nullable = false, length = 6)
    private String id;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "id_project_group", nullable = false, insertable = false, updatable = false)
    private ProjectGroup projectGroup;
}
