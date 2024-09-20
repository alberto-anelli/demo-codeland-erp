package it.codeland.support.managementcontrol.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "generic_cost")
public class GenericCost extends ErpAuditableEntity<Long> {
    @Id
    @Column(name = "year", nullable = false)
    private Long id;

    @Column(name = "amount", nullable = false)
    private Float amount;

}
