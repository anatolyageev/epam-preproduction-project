package com.epam.anatolii.ageev.eshop.admin_services.strategy;

import com.epam.anatolii.ageev.eshop.domain.Computer;
import com.epam.anatolii.ageev.eshop.domain.Item;
import com.epam.anatolii.ageev.eshop.domain.Laptop;

public class BuildLaptop extends BuildComputer {

    public BuildLaptop(Strategy strategy) {
        super(strategy);
    }

    @Override
    protected Item createItem() {
        return new Laptop();
    }

    @Override
    public void initItem(Item item) {
        super.initItem(item);
        Laptop laptop =(Laptop)item;
        laptop.setScreenSize(strategy.initScreenSize());
    }

    public String builderName(){
        return " Create new Laptop";
    }
}
