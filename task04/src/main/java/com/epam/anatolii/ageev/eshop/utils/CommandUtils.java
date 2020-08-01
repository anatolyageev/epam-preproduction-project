package com.epam.anatolii.ageev.eshop.utils;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.command.CommandContainer;
import com.epam.anatolii.ageev.eshop.constants.CommandConstants;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class CommandUtils {

    public static Date enterDate() {
        SimpleDateFormat format = new SimpleDateFormat("MM.dd.yyyy hh:mm");
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

    public static BigDecimal getTotalPrice(ComputerShop computerShop, Map<Long, Integer> cart) {
        BigDecimal totalPrice = new BigDecimal(0);
        for(Map.Entry<Long, Integer> entry : cart.entrySet()){
            totalPrice = totalPrice.add(computerShop.getItemsService()
                    .getOne(entry.getKey()).getPrice()
                    .multiply(new BigDecimal(entry.getValue())));
        }
        return totalPrice;
    }

    public static void printOrder(ComputerShop computerShop, Map<Long, Integer> orderContains) {
        for(Map.Entry<Long,Integer> entry : orderContains.entrySet()){
            System.out.println( computerShop.getItemsService().getOne(entry.getKey()) + " - "
                    + entry.getValue() + ((entry.getValue()>1) ? " items.":" item."));
        }
    }
}
