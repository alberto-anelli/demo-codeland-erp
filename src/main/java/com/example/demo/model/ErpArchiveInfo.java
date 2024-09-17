package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Embeddable
public class ErpArchiveInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    private Boolean archived = false;

    @Column
    private OffsetDateTime archivedDate;

    @Column
    private String archivedBy;
}
