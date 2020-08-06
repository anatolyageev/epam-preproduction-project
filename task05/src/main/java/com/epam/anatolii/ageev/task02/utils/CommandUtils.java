package com.epam.anatolii.ageev.task02.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CommandUtils {

    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public static final String DATE_REGEX = "\\d{2}\\.\\d{2}\\.\\d{4}";

    public static int userInput(String message) {
        Scanner scanner = new Scanner(System.in);
        String choice;
        System.out.println(message);
        while (true) {
            choice = scanner.nextLine().trim();
            if (checkUserInput(choice)) {
                return Integer.parseInt(choice);
            }
            System.out.println("Please enter 1 or 0");
        }
    }

    public static String stringFilter(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine().trim();
    }

    public static Long longFilter(String message) {
        Scanner scanner = new Scanner(System.in);
        Long result = 0L;
        System.out.println(message);
        while (true) {
            try {
                result = scanner.nextLong();
            } catch (NumberFormatException ex) {
                System.out.println("Please enter digits. ");
            }
            return result;
        }
    }

    private static boolean checkUserInput(String chose) {
        if (chose.equals("0") || chose.equals("1")) {
            return true;
        }
        return false;
    }


    public static Date enterDate(String message) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        format.setLenient(false);
        Scanner scanner = new Scanner(System.in);
        System.out.println(message + " using the next format: " + DATE_FORMAT);

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
}
