package com.epam.anatolii.ageev.eshop.view;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.command.Command;
import com.epam.anatolii.ageev.eshop.command.CommandContainer;

import java.util.Map;
import java.util.Scanner;

public class Menu {

    public void menuRunner(ComputerShop computerShop) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            Integer commandId = -1;
            try {
                commandId = Integer.valueOf(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter digits");
            }
            Command command = CommandContainer.get(commandId);
            command.execute(computerShop);
        }
    }

    public void printMenu() {
        StringBuilder sb = new StringBuilder("Menu:" + System.lineSeparator());
        for (Map.Entry entry : CommandContainer.getCommands().entrySet()) {
            sb.append(entry.getKey()).append(" - ");
            Command command = (Command) entry.getValue();
            sb.append(command.commandName() + System.lineSeparator());
        }
        System.out.println(sb.toString());
    }
}
