package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class PartnerProjectId implements Serializable {
    @Column(name = "id_project", nullable = false)
    private Long idProject;
    @Column(name = "id_partner", nullable = false)
    private Long idPartner;
    @Column(name = "month", nullable = false)
    private String month;
}
