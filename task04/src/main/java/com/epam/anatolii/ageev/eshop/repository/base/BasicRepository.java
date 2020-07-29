package com.epam.anatolii.ageev.eshop.repository.base;


import java.util.Collection;
import java.util.Map;

public interface BasicRepository<ID, ENTITY> {
    ENTITY getOne(ID id);
    ENTITY update(ENTITY entity);
    ENTITY insert(ENTITY entity);
    void delete(ID id);
    Collection<ENTITY> findAll();
}
