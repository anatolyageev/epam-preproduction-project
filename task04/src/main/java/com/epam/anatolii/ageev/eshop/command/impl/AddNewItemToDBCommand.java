package com.epam.anatolii.ageev.eshop.command.impl;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.admin_services.strategy.BuildItem;
import com.epam.anatolii.ageev.eshop.admin_services.strategy.ItemBuildContainer;
import com.epam.anatolii.ageev.eshop.command.Command;
import com.epam.anatolii.ageev.eshop.command.CommandContainer;
import com.epam.anatolii.ageev.eshop.domain.Item;

import java.util.Map;
import java.util.Scanner;

public class AddNewItemToDBCommand implements Command {

    @Override
    public void execute(ComputerShop computerShop) {
        BuildItem buildItem = billderSelector(computerShop.getItemBuildContainer());
        Item item = buildItem.buld();
        computerShop.getItemsService().insert(item);
        System.out.println("Item "+ item + " was successfully added to DB.");
    }

    @Override
    public String commandName() {
        return "Add new item to DB;";
    }

    private void printBuildMenu(Map<Integer, BuildItem> map) {
        StringBuilder sb = new StringBuilder("Please choose item to create:" + System.lineSeparator());
        map.forEach((key, value) -> {
            sb.append(key).append(" - ")
                    .append(value.builderName())
                    .append(System.lineSeparator());
        });
        System.out.println(sb.toString());
    }

    private BuildItem billderSelector(ItemBuildContainer buildContainer){
        Scanner scanner = new Scanner(System.in);
        int builderId = -1;
        //BuildItem buildItem;
        while (true) {
            printBuildMenu(buildContainer.getBuilders());
            try {
                builderId = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter digits");
            }
            if(buildContainer.getBuilder(builderId)!=null) {
                return buildContainer.getBuilder(builderId);
            }else {
                System.out.println("Please enter correct values.");
            }
        }
    }
}
