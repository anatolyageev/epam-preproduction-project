package com.epam.anatolii.ageev.eshop.repository.impl;

import com.epam.anatolii.ageev.eshop.repository.CartRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CartRepositoryImpl implements CartRepository {
    private Map<Long, Integer> clientCart;

    public CartRepositoryImpl() {
        this.clientCart = new HashMap<>();
    }

    @Override
    public void update(Long id, int itemNumber) {
        clientCart.put(id, itemNumber);
    }

    @Override
    public void add(Long id) {
        if (!clientCart.containsKey(id)) {
            clientCart.put(id, 1);
        } else {
            update(id, clientCart.get(id) + 1);
        }
    }

    @Override
    public void delete(Long id) {
        clientCart.remove(id);
    }

    @Override
    public Map<Long, Integer> findAll() {
        return Collections.unmodifiableMap(clientCart);
    }
}
