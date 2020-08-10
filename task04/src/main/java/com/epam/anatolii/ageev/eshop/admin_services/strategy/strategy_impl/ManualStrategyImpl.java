package com.epam.anatolii.ageev.eshop.admin_services.strategy.strategy_impl;

import com.epam.anatolii.ageev.eshop.admin_services.strategy.Strategy;
import com.epam.anatolii.ageev.eshop.utils.CommandUtils;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Scanner;

public class ManualStrategyImpl implements Strategy {
    private SecureRandom random = new SecureRandom();
    Scanner scanner = new Scanner(System.in);

    @Override
    public Long initId() {
        return random.nextLong();
    }

    @Override
    public BigDecimal initPrice() {
        System.out.println("Enter price: ");
        while (true) {
            String input = scanner.nextLine().trim();
            if (CommandUtils.digitCheck(input)) {
                return new BigDecimal(input);
            } else {
                System.out.println("Please input correct price.");
            }
        }
    }

    @Override
    public String initProcessorType() {
        System.out.println("Enter processor type: ");
        return scanner.nextLine().trim();
    }

    @Override
    public Double initProcessorFrequency() {
        System.out.println("Enter processor frequency: ");
        while (true) {
            String input = scanner.nextLine().trim();
            if (CommandUtils.digitCheck(input)) {
                return Double.parseDouble(input);
            } else {
                System.out.println("Please input correct processor frequency.");
            }
        }
    }

    @Override
    public Integer initInstalledMemory() {
        System.out.println("Enter Installed Memory: ");
        while (true) {
            String input = scanner.nextLine().trim();
            if (CommandUtils.digitCheck(input)) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Please input correct Installed Memory.");
            }
        }
    }

    @Override
    public String initFormFactor() {
        System.out.println("Enter form factor: ");
        return scanner.nextLine().trim();
    }

    @Override
    public Double initScreenSize() {
        System.out.println("Enter Screen Size: ");
        while (true) {
            String input = scanner.nextLine().trim();
            if (CommandUtils.digitCheck(input)) {
                return Double.parseDouble(input);
            } else {
                System.out.println("Please input correct screen size.");
            }
        }
    }

    @Override
    public Integer initCpuNumber() {
        System.out.println("Enter Cpu Number: ");
        while (true) {
            String input = scanner.nextLine().trim();
            if (CommandUtils.digitCheck(input)) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Please input correct Cpu Number.");
            }
        }
    }

    @Override
    public Boolean initIsIpmi() {
        System.out.println("Is IPMI exist Y/N: ");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("Y") || input.equals("y")) {
                return true;
            }
            if(input.equals("N") || input.equals("n")){
                return false;
            }
            else {
                System.out.println("Please input Y or N.");
            }
        }
    }
}
