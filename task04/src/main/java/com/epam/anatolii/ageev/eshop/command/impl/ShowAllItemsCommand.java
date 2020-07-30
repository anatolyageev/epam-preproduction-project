package com.epam.anatolii.ageev.eshop.command.impl;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.command.Command;

public class ShowAllItemsCommand implements Command {
    @Override
    public void execute(ComputerShop computerShop) {
        computerShop.getItemsService().findAll().stream().forEach(item -> {
            System.out.println(item);
        });
    }

    @Override
    public String commandName() {
        return "Show all items;";
    }
}
