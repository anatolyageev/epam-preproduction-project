package com.epam.anatolii.ageev.eshop.repository;

import java.util.Date;
import java.util.Map;
import java.util.NavigableMap;

public interface OrderRepository {
    Map.Entry<Date, Map<Long, Integer>> getOne(Date date);

    void insert(Date date, Map<Long, Integer> cart);

    NavigableMap<Date, Map<Long, Integer>> findAll();

    NavigableMap<Date, Map<Long, Integer>> findAll(Date dateFrom, Date dateTo);
}
