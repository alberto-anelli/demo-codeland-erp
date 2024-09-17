package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ProjectGroupYearEstimationId implements Serializable {
    @Column(name = "id_project_group", nullable = false)
    private Long idProjectGroup;
    @Column(name = "year", nullable = false)
    private int year;
}
