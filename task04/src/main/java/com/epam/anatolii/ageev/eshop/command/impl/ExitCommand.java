package com.epam.anatolii.ageev.eshop.command.impl;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.command.Command;
import com.epam.anatolii.ageev.eshop.utils.SerializeUtils;

public class ExitCommand implements Command {
    @Override
    public void execute(ComputerShop computerShop) {
        SerializeUtils serializeUtils = new SerializeUtils();
        System.out.println("See you!");
        serializeUtils.serialize(computerShop.getItemsService());
        System.exit(0);
    }

    @Override
    public String commandName() {
        return "Exit.";
    }
}
