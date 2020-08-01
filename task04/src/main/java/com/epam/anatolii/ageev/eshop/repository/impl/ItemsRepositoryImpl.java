package com.epam.anatolii.ageev.eshop.repository.impl;

import com.epam.anatolii.ageev.eshop.domain.Item;
import com.epam.anatolii.ageev.eshop.repository.ItemsRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ItemsRepositoryImpl implements ItemsRepository {

    private Map<Long, Item> itemDb;

    public ItemsRepositoryImpl() {
        this.itemDb = new HashMap<>();
    }

    @Override
    public Item getOne(Long id) {
        checkItemId(id);
        return itemDb.get(id);
    }

    @Override
    public Item update(Item item) {
        checkItemId(item.getId());
        itemDb.put(item.getId(), item);
        return item;
    }

    @Override
    public Item insert(Item item) {
        item.setId(itemDb.size() + 1L);
        return itemDb.put(item.getId(), item);
    }

    @Override
    public void delete(Long id) {
        checkItemId(id);
        itemDb.remove(id);
    }

    @Override
    public Collection<Item> findAll() {
        return itemDb.values();
    }

    private void checkItemId(Long id) {
        if (!itemDb.containsKey(id)) {
            throw new NoSuchElementException("ID does not exist in DB");
        }
    }
}
