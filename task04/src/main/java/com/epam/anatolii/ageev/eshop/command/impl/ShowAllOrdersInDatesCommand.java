package com.epam.anatolii.ageev.eshop.command.impl;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.command.Command;

public class ShowAllOrdersInDatesCommand implements Command {
    @Override
    public void execute(ComputerShop computerShop) {

    }

    @Override
    public String commandName() {
        return "Show all orders in exact period;";
    }
}
