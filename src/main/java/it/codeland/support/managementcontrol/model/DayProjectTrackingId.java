package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Embeddable
public class DayProjectTrackingId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Column(name = "id_project", nullable = false)
    private Long idProject;
    @Column(name = "id_collaborator", nullable = false)
    private Long idCollaborator;
    @Column(name = "day", nullable = false)
    private LocalDate day;
}
