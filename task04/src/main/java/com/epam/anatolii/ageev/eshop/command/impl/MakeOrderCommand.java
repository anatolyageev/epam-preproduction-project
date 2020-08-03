package com.epam.anatolii.ageev.eshop.command.impl;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.command.Command;
import com.epam.anatolii.ageev.eshop.command.CommandContainer;
import com.epam.anatolii.ageev.eshop.constants.CommandConstants;
import com.epam.anatolii.ageev.eshop.utils.CommandUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MakeOrderCommand implements Command {

    @Override
    public void execute(ComputerShop computerShop) {
        Map<Long, Integer> cart = computerShop.getCartService().findAll();

        if (cart.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }

        Date orderDate = CommandUtils.enterDate();
        computerShop.getOrderService().insert(orderDate, cart);
        CommandContainer.get(CommandConstants.SHOW_ALL_ITEMS_IN_CART).execute(computerShop);
        System.out.println("\nTotal price for order: " + CommandUtils.getTotalPrice(computerShop, cart));
        computerShop.getCartService().clear();
    }

    @Override
    public String commandName() {
        return "Make an order;";
    }
}
