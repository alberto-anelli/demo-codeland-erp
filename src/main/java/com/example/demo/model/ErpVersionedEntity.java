package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class ErpVersionedEntity<ID extends Serializable>
        extends ErpEntity<ID> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column
    @Version
    Integer version = 0;

}
