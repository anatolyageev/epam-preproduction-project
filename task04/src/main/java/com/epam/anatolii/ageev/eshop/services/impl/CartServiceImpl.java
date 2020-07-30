package com.epam.anatolii.ageev.eshop.services.impl;

import com.epam.anatolii.ageev.eshop.repository.CartRepository;
import com.epam.anatolii.ageev.eshop.services.CartService;

import java.util.Map;

public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void update(Long id, int itemNumber) {
        cartRepository.update(id, itemNumber);
    }

    @Override
    public void add(Long id) {
        cartRepository.add(id);
    }

    @Override
    public void delete(Long id) {
        cartRepository.delete(id);
    }

    @Override
    public Map<Long, Integer> findAll() {
        return cartRepository.findAll();
    }
}
