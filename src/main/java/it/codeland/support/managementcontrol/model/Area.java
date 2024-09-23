package it.codeland.support.managementcontrol.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "job_role")
public class JobRole extends ErpAuditableEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_job_role")
    Long id;

    @Column(name = "name")
    String name;
}
