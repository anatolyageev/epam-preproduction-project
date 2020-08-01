package com.epam.anatolii.ageev.eshop.command;

import com.epam.anatolii.ageev.eshop.command.impl.*;
import java.util.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommandContainer {
    private static Map<Integer, Command> commands = new LinkedHashMap<>();
    private static final int NO_SUCH_COMMAND = 404;
    private static final int EXIT = 0;
    private static final int SHOW_ALL_ITEMS_COMMAND = 1;
    private static final int ADD_ITEM_TO_CART_COMMAND = 2;
    private static final int MAKE_ORDER_COMMAND = 3;
    private static final int SHOW_ALL_ORDERS_IN_DATES_COMMAND = 4;
    private static final int SHOW_ALL_ITEMS_IN_CART = 5;
    private static final int SHOW_ORDER_BY_DATE = 6;
    private static final int SHOW_LAST_FIVE_ITEMS = 7;

    static {
        commands.put(SHOW_ALL_ITEMS_COMMAND, new ShowAllItemsCommand());
        commands.put(ADD_ITEM_TO_CART_COMMAND, new AddItemToCartCommand());
        commands.put(MAKE_ORDER_COMMAND, new MakeOrderCommand());
        commands.put(SHOW_ALL_ORDERS_IN_DATES_COMMAND, new ShowAllOrdersInDatesCommand());
        commands.put(SHOW_ALL_ITEMS_IN_CART, new ShowAllItemsFormCartCommand());
        commands.put(SHOW_ORDER_BY_DATE, new ShowOrderCommand());
        commands.put(SHOW_LAST_FIVE_ITEMS, new ShowLastFiveItemsCommand());

        commands.put(NO_SUCH_COMMAND, new NoCommand());
        commands.put(EXIT, new ExitCommand());
    }

    /**
     * Returns command object with the given Id.
     *
     * @param commandId Id of the command.
     * @return Command object.
     */
    public static Command get(Integer commandId) {
        if (commandId == null || !commands.containsKey(commandId)) {
            return commands.get(NO_SUCH_COMMAND);
        }
        return commands.get(commandId);
    }

    public static Map<Integer, Command> getCommands (){
        LinkedHashMap<Integer, Command> copyCommand = new LinkedHashMap<>(commands);
        copyCommand.remove(NO_SUCH_COMMAND);
        return copyCommand;
    }

    public static Map<Integer, Command> getCommandsNomodif (){return Collections.unmodifiableMap(commands);}
}
