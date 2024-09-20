package it.codeland.support.managementcontrol.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Embeddable
public class ErpArchiveInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column
    private Boolean archived = false;

    @Column
    private OffsetDateTime archivedDate;

    @Column
    private String archivedBy;
}
