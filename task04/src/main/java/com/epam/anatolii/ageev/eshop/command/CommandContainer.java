package com.epam.anatolii.ageev.eshop.command;

import com.epam.anatolii.ageev.eshop.command.impl.*;
import com.epam.anatolii.ageev.eshop.constants.CommandConstants;

import java.util.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommandContainer {
    private static Map<Integer, Command> commands = new LinkedHashMap<>();

    static {
        commands.put(CommandConstants.SHOW_ALL_ITEMS_COMMAND, new ShowAllItemsCommand());
        commands.put(CommandConstants.ADD_ITEM_TO_CART_COMMAND, new AddItemToCartCommand());
        commands.put(CommandConstants.MAKE_ORDER_COMMAND, new MakeOrderCommand());
        commands.put(CommandConstants.SHOW_ALL_ORDERS_IN_DATES_COMMAND, new ShowAllOrdersInDatesCommand());
        commands.put(CommandConstants.SHOW_ALL_ITEMS_IN_CART, new ShowAllItemsFormCartCommand());
        commands.put(CommandConstants.SHOW_ORDER_BY_DATE, new ShowOrderCommand());
        commands.put(CommandConstants.SHOW_LAST_FIVE_ITEMS, new ShowLastFiveItemsCommand());

        commands.put(CommandConstants.NO_SUCH_COMMAND, new NoCommand());
        commands.put(CommandConstants.EXIT, new ExitCommand());
    }

    /**
     * Returns command object with the given Id.
     *
     * @param commandId Id of the command.
     * @return Command object.
     */
    public static Command get(Integer commandId) {
        if (commandId == null || !commands.containsKey(commandId)) {
            return commands.get(CommandConstants.NO_SUCH_COMMAND);
        }
        return commands.get(commandId);
    }

    public static Map<Integer, Command> getCommands (){
        LinkedHashMap<Integer, Command> copyCommand = new LinkedHashMap<>(commands);
        copyCommand.remove(CommandConstants.NO_SUCH_COMMAND);
        return copyCommand;
    }

    public static Map<Integer, Command> getCommandsNomodif (){return Collections.unmodifiableMap(commands);}
}
