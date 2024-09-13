package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "revenue_stream")
public class RevenueStream {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_revenue", nullable = false)
    private Long idRevenue;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "description", nullable = false, length = 250)
    private String description;

    @Column(name = "amount", nullable = false)
    private float amount;

    @Column(name = "days")
    private int days;

    @Column(name = "notes", length = 250)
    private String notes;

    @ManyToOne
    @JoinColumn(name = "id_project", nullable = false, insertable = false, updatable = false)
    private Project project;

    @Column(name = "id_project")
    private Long idProject;
}
