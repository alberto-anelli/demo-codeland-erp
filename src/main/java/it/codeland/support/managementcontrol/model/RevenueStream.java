package it.codeland.support.managementcontrol.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "revenue_stream")
public class RevenueStream extends ErpAuditableEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_revenue", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "amount", nullable = false)
    private float amount;

    @Column(name = "days")
    private int days;

    @ManyToOne
    @JoinColumn(name = "id_project", nullable = false, insertable = false, updatable = false)
    private Project project;

    @Column(name = "id_project")
    private Long idProject;

    @OneToMany(mappedBy = "revenueStream", fetch = FetchType.LAZY)
    private List<StreamAdvancement> streamAdvancements;
}
