package com.epam.anatolii.ageev.eshop.admin_services.strategy;

import com.epam.anatolii.ageev.eshop.domain.Item;

public abstract class BuildItem {
    protected Strategy strategy;

    public BuildItem(Strategy strategy){
        this.strategy = strategy;
    }

    protected abstract Item createItem();

    public void initItem(Item item){
        item.setId(strategy.initId());
        item.setPrice(strategy.initPrice());
    }

    public Item buld(){
        Item newItem = createItem();
        initItem(newItem);
        return newItem;
    }

    public abstract String builderName();
}
