package com.epam.anatolii.ageev.eshop.services.base;

import java.util.Collection;

public interface BasicService<ID, ENTITY> {
    ENTITY getOne(ID id);

    ENTITY update(ENTITY entity);

    ENTITY insert(ENTITY entity);

    void delete(ID id);

    Collection<ENTITY> findAll();
}
