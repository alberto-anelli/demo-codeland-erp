package it.codeland.support.managementcontrol.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stream_advancement")
public class StreamAdvancement extends ErpAuditableEntity<StreamAdvancementId> {
    @EmbeddedId
    private StreamAdvancementId id;
    @Column(name = "percentage")
    private Float percentage;
    @ManyToOne
    @JoinColumn(name = "id_revenue", nullable = false, insertable = false, updatable = false)
    private RevenueStream revenueStream;
}
