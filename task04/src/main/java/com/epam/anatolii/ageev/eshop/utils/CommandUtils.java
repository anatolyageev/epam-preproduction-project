package com.epam.anatolii.ageev.eshop.utils;

import com.epam.anatolii.ageev.eshop.ComputerShop;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class CommandUtils {
    public static final String DATE_FORMAT = "dd.MM.yyyy HH:mm";
    public static final String DATE_REGEX = "\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}";

    public static Date enterDate() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        format.setLenient(false);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter date in the next format: " + DATE_FORMAT);

        while (true) {
            String inputDate = scanner.nextLine().trim();
            if (!validateDateInput(inputDate)) {
                System.out.println("Data format incorrect. Please try use this format: " + DATE_FORMAT);
                continue;
            }

            Date date = null;
            try {
                date = format.parse(inputDate);
                return date;
            } catch (ParseException e) {
                System.out.println("Data format incorrect. Please try use this format: " + DATE_FORMAT);
            }

        }
    }

    private static boolean validateDateInput(String input) {
        return input.matches(DATE_REGEX);
    }

    public static BigDecimal getTotalPrice(ComputerShop computerShop, Map<Long, Integer> cart) {
        if (computerShop == null || cart == null) {
            return null;
        }

        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            totalPrice = totalPrice.add(computerShop.getItemsService()
                    .getOne(entry.getKey()).getPrice()
                    .multiply(new BigDecimal(entry.getValue())));
        }
        return totalPrice;
    }

    public static void printOrder(ComputerShop computerShop, Map<Long, Integer> orderContains) {
        for (Map.Entry<Long, Integer> entry : orderContains.entrySet()) {
            System.out.println(computerShop.getItemsService().getOne(entry.getKey()) + " - "
                    + entry.getValue() + ((entry.getValue() > 1) ? " items." : " item."));
        }
    }
}
