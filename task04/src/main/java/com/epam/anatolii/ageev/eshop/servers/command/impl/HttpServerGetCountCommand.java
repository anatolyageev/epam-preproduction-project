package com.epam.anatolii.ageev.eshop.servers.command.impl;

import com.epam.anatolii.ageev.eshop.servers.command.ServerCommand;
import com.epam.anatolii.ageev.eshop.services.ItemsService;

public class HttpServerGetCountCommand implements ServerCommand {
    @Override
    public String execute(String fullCommand, ItemsService itemsService) {

        return "{count:" +
                itemsService.findAll().size() +
                "}";
    }
}
