package com.epam.anatolii.ageev.eshop.command.impl;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.command.Command;
import com.epam.anatolii.ageev.eshop.command.CommandContainer;
import com.epam.anatolii.ageev.eshop.constants.CommandConstants;

import java.util.Scanner;

public class AddItemToCartCommand implements Command {
    @Override
    public void execute(ComputerShop computerShop) {
        Scanner scanner = new Scanner(System.in);

        CommandContainer.get(CommandConstants.SHOW_ALL_ITEMS_COMMAND).execute(computerShop);

        System.out.println("\nEnter item id: ");

        do {
            try {
                Long itemId = Long.parseLong(scanner.nextLine().trim());
                if (computerShop.getItemsService().getOne(itemId) != null) {
                    computerShop.getCartService().add(itemId);
                    System.out.println("Item successfully added!");
                    break;
                }else{
                    System.out.println("No such Id in the list, try one more time.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please use digit for Id.");
            }
        } while (true);
    }

    @Override
    public String commandName() {
        return "Add item to cart;";
    }
}
