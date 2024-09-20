package it.codeland.support.managementcontrol.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class StreamAdvancementId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Column(name = "id_project", nullable = false)
    private Long idProject;
    @Column(name = "month", nullable = false, length = 7)
    private String month;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StreamAdvancementId entity = (StreamAdvancementId) o;
        return Objects.equals(this.idProject, entity.idProject) &&
                Objects.equals(this.month, entity.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProject, month);
    }
}
