package it.codeland.support.managementcontrol.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "partner_project")
public class PartnerProject extends ErpAuditableEntity<PartnerProjectId>{

  @EmbeddedId
  private PartnerProjectId id;

  @Column(name = "amount", nullable = false)
  private float amount;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "invoice_nr", length = 20)
  private String invoiceNr;

  @Column(name = "payment_date")
  private LocalDate paymentDate;

  @ManyToOne
  @JoinColumn(name = "id_partner", nullable = false, insertable = false, updatable = false)
  private ExternalPartner externalPartner;

  @ManyToOne
  @JoinColumn(name = "id_project", nullable = false, insertable = false, updatable = false)
  private Project project;

}
