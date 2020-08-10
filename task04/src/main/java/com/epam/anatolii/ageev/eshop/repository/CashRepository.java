package com.epam.anatolii.ageev.eshop.repository;

import com.epam.anatolii.ageev.eshop.domain.Item;

import java.util.Collection;

public interface CashRepository {
   void insert(Long id);

   Collection<Long> findAll();
}
