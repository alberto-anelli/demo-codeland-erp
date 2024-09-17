package com.example.demo.model;

import jakarta.persistence.MappedSuperclass;

import java.io.Serial;
import java.io.Serializable;

@MappedSuperclass
public abstract class ErpEntity<ID extends Serializable> implements VersionSupport, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public abstract ID getId();
    @Override
    public Integer getVersion() {
        return null;
    }
}
