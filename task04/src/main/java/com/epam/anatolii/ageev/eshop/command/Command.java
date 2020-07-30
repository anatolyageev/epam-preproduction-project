package com.epam.anatolii.ageev.eshop.command;

import com.epam.anatolii.ageev.eshop.ComputerShop;

public interface Command {

    void execute(ComputerShop computerShop);

    String commandName();
}
