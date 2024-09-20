package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class ErpEntity<ID extends Serializable> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Column(name = "notes", length = 4000)
    private String notes;
    public abstract ID getId();
}
