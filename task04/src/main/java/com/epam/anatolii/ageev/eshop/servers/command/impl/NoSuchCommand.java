package com.epam.anatolii.ageev.eshop.servers.command.impl;

import com.epam.anatolii.ageev.eshop.servers.command.ServerCommand;
import com.epam.anatolii.ageev.eshop.services.ItemsService;

import static com.epam.anatolii.ageev.eshop.constants.ServerConstants.NO_SUCH_COMMAND;

public class NoSuchCommand implements ServerCommand {
    @Override
    public String execute(String fillCommand, ItemsService itemsService) {
        return NO_SUCH_COMMAND;
    }
}
