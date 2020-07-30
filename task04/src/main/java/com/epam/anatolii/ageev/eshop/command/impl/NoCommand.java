package com.epam.anatolii.ageev.eshop.command.impl;

import com.epam.anatolii.ageev.eshop.command.Command;

public class NoCommand implements Command {
    @Override
    public void execute() {

    }

    @Override
    public String commandName() {
        return null;
    }
}
