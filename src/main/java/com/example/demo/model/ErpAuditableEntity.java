package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class ErpAuditableEntity<ID extends Serializable> extends ErpVersionedEntity<ID> {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, insertable = true, updatable = false)
    @CreatedDate
    protected LocalDateTime createdDate;

    @Column(nullable = false, insertable = true, updatable = false)
    @CreatedBy
    protected String createdBy;

    @Column(nullable = false)
    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;

    @Column(nullable = false)
    @LastModifiedBy
    protected String lastModifiedBy;
}
