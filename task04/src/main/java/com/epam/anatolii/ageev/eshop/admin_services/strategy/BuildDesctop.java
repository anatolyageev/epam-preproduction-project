package com.epam.anatolii.ageev.eshop.admin_services.strategy;

import com.epam.anatolii.ageev.eshop.domain.Computer;
import com.epam.anatolii.ageev.eshop.domain.Desktop;
import com.epam.anatolii.ageev.eshop.domain.Item;

public class BuildDesctop extends BuildComputer {

    public BuildDesctop(Strategy strategy) {
        super(strategy);
    }

    @Override
    protected Item createItem() {
        return new Desktop();
    }

    public void initItem(Item item){
        super.initItem(item);
        Desktop desktop = (Desktop) item;
        desktop.setFormFactor(strategy.initFormFactor());
    }

    public String builderName(){
        return " Create new Desctop;";
    }
}
