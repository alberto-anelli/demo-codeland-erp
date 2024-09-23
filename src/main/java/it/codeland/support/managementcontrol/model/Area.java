package it.codeland.support.managementcontrol.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "area")
public class Area extends ErpAuditableEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area")
    Long id;

    @Column(name = "name")
    String name;
}
