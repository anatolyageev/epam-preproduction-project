package com.epam.anatolii.ageev.eshop.command.impl;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.command.Command;
import com.epam.anatolii.ageev.eshop.utils.CommandUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ShowOrderCommand implements Command {

    SimpleDateFormat format = new SimpleDateFormat("MM.dd.yyyy hh:mm");

    @Override
    public void execute(ComputerShop computerShop) {

        if (computerShop.getOrderService().findAll().isEmpty()) {
            System.out.println("Order list is empty!");


            return;
        }
        Date orderDate = CommandUtils.enterDate();

        Map.Entry<Date, Map<Long, Integer>> orderInfo = computerShop.getOrderService().getOne(orderDate);

        System.out.println("Date and time of the order: " +  format.format(orderInfo.getKey())+
                System.lineSeparator());

        CommandUtils.printOrder(computerShop, orderInfo.getValue());

        System.out.println(System.lineSeparator()+ "Total price for the order: " +
                CommandUtils.getTotalPrice(computerShop, orderInfo.getValue())+
                System.lineSeparator());
    }

    @Override
    public String commandName() {
        return "Find order by date;";
    }
}
