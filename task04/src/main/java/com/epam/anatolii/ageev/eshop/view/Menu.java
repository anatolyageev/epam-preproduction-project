package com.epam.anatolii.ageev.eshop.view;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.command.Command;
import com.epam.anatolii.ageev.eshop.command.CommandContainer;

import java.util.Map;
import java.util.Scanner;

public class Menu {


    public void menuRunner(ComputerShop computerShop){
        Scanner scanner = new Scanner(System.in);
        while (true){
            printMenu();
            Command command = CommandContainer.get(Integer.valueOf(scanner.nextLine().trim()));
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
