package com.epam.anatolii.ageev.eshop.view;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.admin_services.strategy.Mode;
import com.epam.anatolii.ageev.eshop.command.Command;
import com.epam.anatolii.ageev.eshop.command.CommandContainer;

import java.util.Map;
import java.util.Scanner;

public class Menu {

    public Mode initMode(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose mode for fill DB. 1 - Automatic, 0 - Manual: ");
        while (true) {
            try {
               String value =  scanner.nextLine().trim();
               if(value.equals("1")){
                   return Mode.AUTOMATIC;
               }else if(value.equals("0")){
                   return Mode.MANUAL;
               }
            } catch (NumberFormatException ex) {
                System.out.println("Please enter digits");
            }
        }
    }

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
