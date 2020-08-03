package com.epam.anatolii.ageev.eshop.command.impl;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.command.Command;

public class ShowLastFiveItemsCommand implements Command {
    @Override
    public void execute(ComputerShop computerShop) {
        if (computerShop.getCashService().findAll().isEmpty()) {
            System.out.println("You don't buy anything yet :)");
            return;
        }
        computerShop.getCashService().findAll().forEach(id -> {
            System.out.println(computerShop.getItemsService().getOne(id));
        });
    }

    @Override
    public String commandName() {
        return "Show last five items;";
    }
}
