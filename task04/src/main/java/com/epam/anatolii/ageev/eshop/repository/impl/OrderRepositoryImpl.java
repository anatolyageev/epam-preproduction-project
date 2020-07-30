package com.epam.anatolii.ageev.eshop.repository.impl;

import com.epam.anatolii.ageev.eshop.repository.OrderRepository;

import java.util.*;

public class OrderRepositoryImpl implements OrderRepository {
    private NavigableMap<Date, Map<Long, Integer>> orders;

    public OrderRepositoryImpl(){
        this.orders = new TreeMap<>();
    }

    @Override
    public Map.Entry<Date, Map<Long, Integer>> getOne(Date date) {
        return orders.higherEntry(date);
    }

    @Override
    public void insert(Date date, Map<Long, Integer> cart) {
        orders.put(date, cart);
    }

    @Override
    public NavigableMap<Date, Map<Long, Integer>> findAll() {
        return Collections.unmodifiableNavigableMap(orders);
    }

    @Override
    public NavigableMap<Date, Map<Long, Integer>> findAll(Date dateFrom, Date dateTo) {
        return orders.subMap(dateFrom, true, dateTo, true);
    }
}
