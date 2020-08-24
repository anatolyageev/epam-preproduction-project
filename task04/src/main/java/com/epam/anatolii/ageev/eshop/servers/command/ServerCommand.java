package com.epam.anatolii.ageev.eshop.servers.command;

import com.epam.anatolii.ageev.eshop.services.ItemsService;

public interface ServerCommand {
    String execute (String fullCommand, ItemsService itemsService);
}
