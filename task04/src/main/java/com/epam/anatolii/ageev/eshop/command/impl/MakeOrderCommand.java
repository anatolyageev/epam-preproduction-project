package com.epam.anatolii.ageev.eshop.command.impl;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.command.Command;
import com.epam.anatolii.ageev.eshop.command.CommandContainer;
import com.epam.anatolii.ageev.eshop.constants.CommandConstants;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class MakeOrderCommand implements Command {
    private SimpleDateFormat format = new SimpleDateFormat("MM.dd.yyyy HH:mm");

    @Override
    public void execute(ComputerShop computerShop) {
        Map<Long, Integer> cart = computerShop.getCartService().findAll();

        if (cart.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }
        Date orderDate = enterDate();
        BigDecimal totalPrice = new BigDecimal(0);
        for(Map.Entry<Long, Integer> entry : cart.entrySet()){
            System.out.println(computerShop.getItemsService()
                    .getOne(entry.getKey()).getPrice().toString());
            System.out.println(new BigDecimal(entry.getValue()).toString());
            totalPrice = totalPrice.add(computerShop.getItemsService()
                    .getOne(entry.getKey()).getPrice()
                    .multiply(new BigDecimal(entry.getValue())));
        }
        computerShop.getOrderService().insert(orderDate,cart);
        CommandContainer.get(CommandConstants.SHOW_ALL_ITEMS_IN_CART).execute(computerShop);
       // System.out.println("Total price for order: " + getTotalPrice(computerShop, cart));
        System.out.println("Total price for order: " + totalPrice);
    }

    private BigDecimal getTotalPrice(ComputerShop computerShop, Map<Long, Integer> cart) {
        CommandContainer.get(CommandConstants.SHOW_ALL_ITEMS_IN_CART).execute(computerShop);
        System.out.println("in price");
        BigDecimal totalPrice = new BigDecimal(0);
        for(Map.Entry<Long, Integer> entry : cart.entrySet()){
            totalPrice.add(computerShop.getItemsService()
                    .getOne(entry.getKey()).getPrice()
                    .multiply(new BigDecimal(entry.getValue())));
         }
        return totalPrice;
    }

    private Date enterDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter date in the next format: MM.dd.yyyy HH:mm: ");
        while (true) {
            try {
                Date date = format.parse(scanner.nextLine().trim());
                return date;
            } catch (ParseException e) {
                System.out.println("Data with errors. Try one more time.");
            }
        }
    }

    @Override
    public String commandName() {
        return null;
    }
}
