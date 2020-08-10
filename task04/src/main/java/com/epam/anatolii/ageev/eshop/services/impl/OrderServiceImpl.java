package com.epam.anatolii.ageev.eshop.services.impl;

import com.epam.anatolii.ageev.eshop.repository.OrderRepository;
import com.epam.anatolii.ageev.eshop.services.OrderService;

import java.util.Date;
import java.util.Map;
import java.util.NavigableMap;

public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public Map.Entry<Date, Map<Long, Integer>> getOne(Date date) {
        return orderRepository.getOne(date);
    }

    @Override
    public void insert(Date date, Map<Long, Integer> cart) {
        orderRepository.insert(date,cart);
    }

    @Override
    public NavigableMap<Date, Map<Long, Integer>> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public NavigableMap<Date, Map<Long, Integer>> findAll(Date dateFrom, Date dateTo) {
        return orderRepository.findAll(dateFrom,dateTo);
    }
}
