package com.example.demo.model;

import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

@MappedSuperclass
public abstract class ErpEntity<ID extends Serializable> implements VersionSupport {
    private static final long serialVersionUID = 1L;
    public abstract ID getId();
}
