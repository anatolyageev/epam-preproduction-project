package com.epam.anatolii.ageev.eshop.services.impl;

import com.epam.anatolii.ageev.eshop.domain.Item;
import com.epam.anatolii.ageev.eshop.repository.ItemsRepository;
import com.epam.anatolii.ageev.eshop.services.ItemsService;

import java.util.Collection;

public class ItemServiceImpl implements ItemsService {
    private ItemsRepository itemsRepository;

    public  ItemServiceImpl(ItemsRepository itemsRepository){
        this.itemsRepository = itemsRepository;
    }

    @Override
    public Item getOne(Long id) {
        return itemsRepository.getOne(id);
    }

    @Override
    public Item update(Item item) {
        return itemsRepository.update(item);
    }

    @Override
    public Item insert(Item item) {
        return itemsRepository.insert(item);
    }

    @Override
    public void delete(Long id) {
        itemsRepository.delete(id);
    }

    @Override
    public Collection<Item> findAll() {
        return itemsRepository.findAll();
    }
}
