package com.epam.anatolii.ageev.eshop.servers.command.impl;

import com.epam.anatolii.ageev.eshop.domain.Item;
import com.epam.anatolii.ageev.eshop.servers.command.ServerCommand;
import com.epam.anatolii.ageev.eshop.services.ItemsService;
import java.util.Objects;

import static com.epam.anatolii.ageev.eshop.constants.ServerConstants.ID_NOT_EXIST_IN_DB;

public class HttpServerGetItemCommand implements ServerCommand {

    @Override
    public String execute(String fullCommand, ItemsService itemsService) {
        Long id = Long.parseLong(fullCommand.trim().split("=")[1]);
        Item item = itemsService.getOne(id);
        if (Objects.isNull(item)) {
            return ID_NOT_EXIST_IN_DB;
        }
        return "{name:" +
                item.getClass().getSimpleName() +
                ", price: " +
                item.getPrice() +
                "}";
    }
}
