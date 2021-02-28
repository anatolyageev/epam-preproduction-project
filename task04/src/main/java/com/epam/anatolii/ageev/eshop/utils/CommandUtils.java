package com.epam.anatolii.ageev.eshop.utils;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.admin_services.strategy.Strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommandUtils {
    public static final String DATE_FORMAT = "dd.MM.yyyy HH:mm";
    public static final String DATE_REGEX = "\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}";
    public static final String DIGIT_FORMAT = "[+-]?([0-9]*[.])?[0-9]+";


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

    public static boolean digitCheck(String input){
        return input.matches(DIGIT_FORMAT);
    }

    private static boolean validateDateInput(String input) {
        return input.matches(DATE_REGEX);
    }

    public static BigDecimal getTotalPrice(ComputerShop computerShop, Map<Long, Integer> cart) {
        if (computerShop == null) {
            throw new NullPointerException("ComputerShop is Null!");
        }
        if (cart == null) {
            throw new NullPointerException("Cart is Null!");
        }
        if (cart.isEmpty()) {
            throw new NullPointerException("Cart is empty!");
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

    public static Double getRandomDouble(int minPrice, int maxPrice){
        double random = new Random().nextDouble();
        random = minPrice + (random * (maxPrice - minPrice));
        BigDecimal bd = BigDecimal.valueOf(random);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static ResourceBundle getResourceBundle(){

        System.out.println("Please choose language ru/en:");
        return ResourceBundle.getBundle("locale" ,new Locale(validateLocaleInput()));
    }

    private static String validateLocaleInput() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine().trim();
           if(input.equals("ru") || input.equals("en")){
               return input;
           }else {
               System.out.println("Please input ru or en.");
           }
        }
    }
}
