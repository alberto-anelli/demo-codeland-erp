package it.codeland.support.managementcontrol.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "generic_cost")
public class GenericCost extends ErpAuditableEntity<Long> {
    @Id
    @Column(name = "id_generic_cost", nullable = false)
    private Long id;

    @Column(name = "description", length = 4000)
    private String description;

    @Column(name = "id_area", nullable = false)
    private Long idArea;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_area", referencedColumnName = "id_area", insertable = false, updatable = false)
    private JobRole idArea;

    @Column(name = "year", nullable = false)
    private Long year;

    @Column(name = "amount", nullable = false)
    private Float amount;

}
