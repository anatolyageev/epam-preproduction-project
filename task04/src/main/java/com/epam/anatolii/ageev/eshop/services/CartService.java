package com.epam.anatolii.ageev.eshop.services;

import java.util.Map;

public interface CartService {
    void update(Long id, int itemNumber);

    void add(Long id);

    void delete(Long id);

    Map<Long, Integer> findAll();

    void clear();
}
