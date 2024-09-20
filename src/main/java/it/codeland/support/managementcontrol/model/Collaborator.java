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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_collaborator")
    private Long id;

    @Column(name = "code", length = 20)
    private String code;

    @Column(name = "id_company", nullable = false)
    private Long idCompany;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_company", referencedColumnName = "id_company", insertable = false, updatable = false)
    private Company company;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "city", length = 30)
    private String city;

    @Column(name = "region", length = 30)
    private String region;

    @Column(name = "email")
    private String email;

    @Column(name = "phone", length = 30)
    private String phone;

    @OneToMany(mappedBy = "collaborator", fetch = FetchType.LAZY)
    private List<CollaboratorEconomics> collaboratorEconomics;

    @OneToMany(mappedBy = "collaborator", fetch = FetchType.LAZY)
    private List<DayProjectTracking> dayProjectTrackings;
}
