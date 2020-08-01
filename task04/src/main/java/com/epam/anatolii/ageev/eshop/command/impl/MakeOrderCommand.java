package com.epam.anatolii.ageev.eshop.command.impl;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.command.Command;
import com.epam.anatolii.ageev.eshop.command.CommandContainer;
import com.epam.anatolii.ageev.eshop.constants.CommandConstants;
import com.epam.anatolii.ageev.eshop.utils.CommandUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MakeOrderCommand implements Command {
    private SimpleDateFormat format = new SimpleDateFormat("mm.dd.yyyy hh:mm");

    @Override
    public void execute(ComputerShop computerShop) {
        Map<Long, Integer> cart = new HashMap<>();
        cart = computerShop.getCartService().findAll();

        if (cart.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }
        Date orderDate = CommandUtils.enterDate();
        computerShop.getOrderService().insert(orderDate,cart);
        CommandContainer.get(CommandConstants.SHOW_ALL_ITEMS_IN_CART).execute(computerShop);
        System.out.println("\nTotal price for order: " + CommandUtils.getTotalPrice(computerShop, cart));
        computerShop.getCartService().clear();
    }

//    private BigDecimal getTotalPrice(ComputerShop computerShop, Map<Long, Integer> cart) {
//        CommandContainer.get(CommandConstants.SHOW_ALL_ITEMS_IN_CART).execute(computerShop);
//        BigDecimal totalPrice = new BigDecimal(0);
//        for(Map.Entry<Long, Integer> entry : cart.entrySet()){
//            totalPrice = totalPrice.add(computerShop.getItemsService()
//                    .getOne(entry.getKey()).getPrice()
//                    .multiply(new BigDecimal(entry.getValue())));
//         }
//        return totalPrice;
//    }

//    private Date enterDate() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Please enter date in the next format: MM.dd.yyyy HH:mm: ");
//        while (true) {
//            try {
//                Date date = format.parse(scanner.nextLine().trim());
//                return date;
//            } catch (ParseException e) {
//                System.out.println("Data with errors. Try one more time.");
//            }
//        }
//    }

    @Override
    public String commandName() {
        return "Make an order;";
    }
}
