package com.epam.anatolii.ageev.eshop.admin_services.strategy;


import com.epam.anatolii.ageev.eshop.domain.Computer;
import com.epam.anatolii.ageev.eshop.domain.Item;

public abstract class BuildComputer extends BuildItem{

    public BuildComputer(Strategy strategy) {
        super(strategy);
    }

    public void initItem(Item item){
        super.initItem(item);
        Computer computer =(Computer) item;
        computer.setProcessorType(strategy.initProcessorType());
        computer.setProcessorFrequency(strategy.initProcessorFrequency());
        computer.setInstalledMemory(strategy.initInstalledMemory());
    }
    public abstract String builderName();
}
