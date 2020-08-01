package com.epam.anatolii.ageev.eshop.command.impl;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.command.Command;

public class ExitCommand implements Command {
    @Override
    public void execute(ComputerShop computerShop) {
        System.out.println("Good by!");
        System.exit(0);
    }

    @Override
    public String commandName() {
        return "Exit.";
    }
}
