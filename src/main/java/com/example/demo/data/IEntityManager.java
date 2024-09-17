package com.example.demo.data;

import com.example.demo.model.ErpEntity;

import java.io.Serializable;

public interface IEntityManager<T extends ErpEntity<ID>, ID extends Serializable> {
    T patchEntity(ID id, T entity);
}
