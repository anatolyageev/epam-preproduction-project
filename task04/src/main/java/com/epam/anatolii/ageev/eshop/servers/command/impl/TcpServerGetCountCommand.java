package com.epam.anatolii.ageev.eshop.servers.command.impl;

import com.epam.anatolii.ageev.eshop.servers.command.ServerCommand;
import com.epam.anatolii.ageev.eshop.services.ItemsService;

public class TcpServerGetCountCommand implements ServerCommand {
    @Override
    public String execute(String fullCommand, ItemsService itemsService) {
        return "Number items in the shop: " + itemsService.findAll().size();
    }
}
