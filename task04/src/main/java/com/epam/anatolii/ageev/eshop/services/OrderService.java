package com.epam.anatolii.ageev.eshop.services;

import java.util.Date;
import java.util.Map;
import java.util.NavigableMap;

public interface OrderService {
    Map.Entry<Date, Map<Long, Integer>> getOne(Date date);

    void insert(Date date, Map<Long, Integer> cart);

    NavigableMap<Date, Map<Long, Integer>> findAll();

    NavigableMap<Date, Map<Long, Integer>> findAll(Date dateFrom, Date dateTo);
}
