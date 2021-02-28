package com.epam.anatolii.ageev.eshop.servers.command.impl;

import com.epam.anatolii.ageev.eshop.domain.Computer;
import com.epam.anatolii.ageev.eshop.servers.command.ServerCommand;
import com.epam.anatolii.ageev.eshop.services.ItemsService;

public class TcpServerGetItemCommand implements ServerCommand {
    @Override
    public String execute(String fullCommand, ItemsService itemsService) {
        Long id = Long.parseLong(fullCommand.trim().split("=")[1]);
        Computer item = (Computer) itemsService.getOne(id);
        return item.getClass().getSimpleName() + " | " + item.getPrice();
    }
}
