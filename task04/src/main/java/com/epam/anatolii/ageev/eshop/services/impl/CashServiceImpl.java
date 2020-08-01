package com.epam.anatolii.ageev.eshop.services.impl;

import com.epam.anatolii.ageev.eshop.repository.CashRepository;
import com.epam.anatolii.ageev.eshop.services.CashService;

import java.util.Collection;

public class CashServiceImpl implements CashService {
    private CashRepository cashRepository;

    public CashServiceImpl(CashRepository cashRepository){
        this.cashRepository = cashRepository;
    }

    @Override
    public void insert(Long id) {
        cashRepository.insert(id);
    }

    @Override
    public Collection<Long> findAll() {
        return cashRepository.findAll();
    }
}
