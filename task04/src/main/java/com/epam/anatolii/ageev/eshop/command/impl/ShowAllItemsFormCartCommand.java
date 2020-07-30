package com.epam.anatolii.ageev.eshop.command.impl;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.command.Command;

public class ShowAllItemsFormCartCommand implements Command {
    @Override
    public void execute(ComputerShop computerShop) {
        if(computerShop.getCartService().findAll().isEmpty()){
            System.out.println("\nCart is empty.\n");
            return;
        }

        computerShop.getCartService().findAll().forEach((id,quantity) -> {
            System.out.println(computerShop.getItemsService().getOne(id) + " - "
                    + quantity + ((quantity>1) ? " items.":" item."));
        });
    }

    @Override
    public String commandName() {
        return "Show all items in the cart;";
    }
}
