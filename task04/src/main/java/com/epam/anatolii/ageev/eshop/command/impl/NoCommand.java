package com.epam.anatolii.ageev.eshop.command.impl;


import com.epam.anatolii.ageev.eshop.ComputerShop;

import com.epam.anatolii.ageev.eshop.command.Command;

public class NoCommand implements Command {
    @Override
    public void execute(ComputerShop computerShop) {
        System.out.println("No such command exist! Please enter correct number.");
    }

    @Override
    public String commandName() {
        return "No such command;";
    }
}
