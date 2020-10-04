package com.epam.anatolii.ageev.repository;

import com.epam.anatolii.ageev.domain.Order;

public interface OrderRepository {
    Order createOrder(Order order);
}
