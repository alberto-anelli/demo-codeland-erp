package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "COLLABORATORS")
public class Collaborator extends ErpAuditableEntity<Long> {

    @Serial
    private static final long serialVersionUID = -1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_collaborator", length = 20)
    private Long id;

    @Column(name = "code", length = 20)
    private String code;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "hiring_date", nullable = false)
    private LocalDate hiringDate;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone", length = 30)
    private String phone;

    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "collaborator", fetch = FetchType.LAZY)
    private List<CollaboratorEconomics> collaboratorEconomics;

    @OneToMany(mappedBy = "collaborator", fetch = FetchType.LAZY)
    private List<DayProjectTracking> dayProjectTrackings;
}
