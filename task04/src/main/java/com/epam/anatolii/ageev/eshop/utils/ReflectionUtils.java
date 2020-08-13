package com.epam.anatolii.ageev.eshop.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class ReflectionUtils {
    private static Map<Class, Supplier> map = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static String inputString(){
        return scanner.nextLine().trim();
    }

    public static Double inputDouble(){
        while (true) {
            String input = scanner.nextLine().trim();
            if (CommandUtils.digitCheck(input)) {
                return Double.parseDouble(input);
            } else {
                System.out.println("Please input correct double value.");
            }
        }
    }

    public static BigDecimal inputBigDecimal(){
        while (true) {
            String input = scanner.nextLine().trim();
            if (CommandUtils.digitCheck(input)) {
                return new BigDecimal(input);
            } else {
                System.out.println("Please input correct BigDecimal value.");
            }
        }
    }

    public static Integer inputInteger(){
        while (true) {
            String input = scanner.nextLine().trim();
            if (CommandUtils.digitCheck(input)) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Please input integer value.");
            }
        }
    }

    public static Long inputLong() {
        while (true) {
            String input = scanner.nextLine().trim();
            if (CommandUtils.digitCheck(input)) {
                return Long.parseLong(input);
            } else {
                System.out.println("Please input correct long value.");
            }
        }
    }

    public static Boolean inputBoolean() {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("Y") || input.equals("y")) {
                return true;
            }
            if(input.equals("N") || input.equals("n")){
                return false;
            }
            else {
                System.out.println("Please input Y/N or y/n.");
            }
        }
    }

    static {
        map.put(String.class, ReflectionUtils::inputString);
        map.put(Double.class, ReflectionUtils::inputDouble);
        map.put(Integer.class, ReflectionUtils::inputInteger);
        map.put(Long.class, ReflectionUtils::inputLong);
        map.put(BigDecimal.class, ReflectionUtils::inputBigDecimal);
        map.put(Boolean.class, ReflectionUtils::inputBoolean);
    }

    public static Supplier getSupplier(Class type){
        return map.get(type);
    }

}
