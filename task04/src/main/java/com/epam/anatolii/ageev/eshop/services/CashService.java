package com.epam.anatolii.ageev.eshop.services;

import java.util.Collection;

public interface CashService {
    void insert(Long id);

    Collection<Long> findAll();
}
