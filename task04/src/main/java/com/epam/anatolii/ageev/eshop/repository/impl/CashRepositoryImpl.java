package com.epam.anatolii.ageev.eshop.repository.impl;

import com.epam.anatolii.ageev.eshop.domain.Item;
import com.epam.anatolii.ageev.eshop.repository.CashRepository;

import java.util.*;

public class CashRepositoryImpl implements CashRepository {
    private Map<Long, Date> cash;
    private final int MAX_ENTRIES = 5;

    public CashRepositoryImpl(){
        cash = new LinkedHashMap<Long, Date>(MAX_ENTRIES + 1, .75F, false) {
            protected boolean removeEldestEntry(Map.Entry<Long, Date> eldest) {
                return size() > MAX_ENTRIES;
            }
        };
    }

    @Override
    public void insert(Long id) {
        cash.put(id,new Date(System.currentTimeMillis()));
    }

    @Override
    public Collection<Long> findAll() {
        return cash.keySet();
    }
}
