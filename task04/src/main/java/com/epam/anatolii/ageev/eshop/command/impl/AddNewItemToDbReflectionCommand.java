package com.epam.anatolii.ageev.eshop.command.impl;

import com.epam.anatolii.ageev.eshop.ComputerShop;
import com.epam.anatolii.ageev.eshop.admin_services.strategy.BuildItem;
import com.epam.anatolii.ageev.eshop.admin_services.strategy.ItemBuildContainer;
import com.epam.anatolii.ageev.eshop.command.Command;
import com.epam.anatolii.ageev.eshop.domain.Item;
import com.epam.anatolii.ageev.eshop.reflaction_fill.BuildItemReflaction;
import com.epam.anatolii.ageev.eshop.reflaction_fill.ItemBuildConteinerReflection;
import com.epam.anatolii.ageev.eshop.utils.CommandUtils;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AddNewItemToDbReflectionCommand implements Command {
    @Override
    public void execute(ComputerShop computerShop) {
        ResourceBundle resourceBundle = CommandUtils.getResourceBundle();
        ItemBuildConteinerReflection itemBuildConteinerReflection = new ItemBuildConteinerReflection();
        BuildItemReflaction buildItemReflaction = new BuildItemReflaction(computerShop.getFILL_MODE(), resourceBundle);

        Item item = itemSelector(itemBuildConteinerReflection, resourceBundle);
        buildItemReflaction.getState().build(item);
        computerShop.getItemsService().insert(item);
        System.out.println("Item " + item + " was successfully added to DB.");
    }

    @Override
    public String commandName() {
        return "Add with reflection;";
    }

    private void printBuildMenu(Map<Integer, Item> map, ResourceBundle resourceBundle) {

        StringBuilder sb = new StringBuilder(resourceBundle.getString("CHOOSE_ITEM") + System.lineSeparator());
        map.forEach((key, value) -> sb.append(key).append(" - ")
                .append(value.getClass().getSimpleName())
                .append(System.lineSeparator()));
        System.out.println(sb.toString());
    }

    private Item itemSelector(ItemBuildConteinerReflection itemBuildConteinerReflection, ResourceBundle resourceBundle) {
        Scanner scanner = new Scanner(System.in);

        int builderId = -1;
        while (true) {
            printBuildMenu(itemBuildConteinerReflection.getBuilders(),resourceBundle);
            try {
                builderId = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println(resourceBundle.getString("PLEASE_ENTER_DIGITS"));
            }
            if (itemBuildConteinerReflection.getItem(builderId) != null) {
                return itemBuildConteinerReflection.getItem(builderId);
            } else {
                System.out.println("Please enter correct values.");
            }
        }
    }
}
