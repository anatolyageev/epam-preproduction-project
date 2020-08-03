package com.epam.anatolii.ageev.eshop.command.impl;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.command.Command;
import com.epam.anatolii.ageev.eshop.utils.CommandUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.NavigableMap;

public class ShowAllOrdersInDatesCommand implements Command {
    SimpleDateFormat format = new SimpleDateFormat(CommandUtils.DATE_FORMAT);

    @Override
    public void execute(ComputerShop computerShop) {
        if (computerShop.getOrderService().findAll().isEmpty()) {
            System.out.println("Order list is empty!");
            return;
        }
        System.out.println("Please enter period from: ");
        Date orderDateFrom = CommandUtils.enterDate();

        System.out.println("to: ");

        Date orderDateTo = CommandUtils.enterDate();

        NavigableMap<Date, Map<Long, Integer>> orderInfo = computerShop.getOrderService().findAll(orderDateFrom, orderDateTo);

        for (Map.Entry order : orderInfo.entrySet()) {
            System.out.println("Date and time of the order: " + format.format(order.getKey()) +
                    System.lineSeparator());
            CommandUtils.printOrder(computerShop, (Map) order.getValue());
            System.out.println(System.lineSeparator() + "Total price for the order: " +
                    CommandUtils.getTotalPrice(computerShop, (Map) order.getValue()) +
                    System.lineSeparator());
        }
    }

    @Override
    public String commandName() {
        return "Show all orders in exact period;";
    }
}
