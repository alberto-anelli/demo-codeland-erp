package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Embeddable
public class ErpDeleteInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    private Boolean deleted = false;

    @Column
    private OffsetDateTime deletedDate;

    @Column
    private String deletedBy;
}
