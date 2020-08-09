package com.epam.anatolii.ageev.eshop.admin_services.strategy;

import com.epam.anatolii.ageev.eshop.domain.Desktop;
import com.epam.anatolii.ageev.eshop.domain.Item;
import com.epam.anatolii.ageev.eshop.domain.Server;

public class BuildServer extends BuildDesctop {

    public BuildServer(Strategy strategy) {
        super(strategy);
    }

    @Override
    protected Item createItem() {
        return new Server();
    }

    public void initItem(Item item) {
        super.initItem(item);
        Server server = (Server)item;
        server.setCpuNumber(strategy.initCpuNumber());
        server.setIpmi(strategy.initIsIpmi());
    }

    public String builderName(){
        return " Create new Server;";
    }
}
