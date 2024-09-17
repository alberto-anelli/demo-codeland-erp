package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "external_partner")
public class ExternalPartner extends ErpAuditableEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partner", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "vat", length = 16)
    private String vat;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "sdi", length = 20)
    private String sdi;

    @Column(name = "phone", length = 30)
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "externalPartner", fetch = FetchType.LAZY)
    private List<PartnerProject> partnerProjects;
}
