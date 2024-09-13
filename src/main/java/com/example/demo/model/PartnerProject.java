package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "partner_project")
@IdClass(PartnerProjectId.class)
public class PartnerProject {

  @Id
  @Column(name = "id_project")
  private Long idProject;

  @Id
  @Column(name = "id_partner")
  private Long idPartner;

  @Id
  @Column(name = "month")
  private String month;

  @Column(name = "amount", nullable = false)
  private float amount;

  @Column(name = "description", nullable = false, length = 250)
  private String description;

  @Column(name = "invoiceNr", length = 20)
  private String invoiceNr;

  @Column(name = "payment_date")
  private LocalDate paymentDate;

  @Column(name = "notes", length = 250)
  private String notes;

  @ManyToOne
  @JoinColumn(name = "id_partner", nullable = false, insertable = false, updatable = false)
  private ExternalPartner externalPartner;

  @ManyToOne
  @JoinColumn(name = "id_project", nullable = false, insertable = false, updatable = false)
  private Project project;

}
