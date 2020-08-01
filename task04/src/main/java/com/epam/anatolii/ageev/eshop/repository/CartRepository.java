package com.epam.anatolii.ageev.eshop.repository;

import java.util.Map;

public interface CartRepository {
    void update(Long id, int itemNumber);

    void add(Long id);

    void delete(Long id);

    Map<Long, Integer> findAll();

    void clear();
}
