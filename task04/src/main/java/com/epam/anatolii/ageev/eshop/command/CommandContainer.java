package com.epam.anatolii.ageev.eshop.command;

import com.epam.anatolii.ageev.eshop.command.impl.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static Map<Integer, Command> commands = new LinkedHashMap<>();
    private static final int NO_SUCH_COMMAND = 404;
    private static final int EXIT = 0;
    private static final int SHOW_ALL_ITEMS_COMMAND = 1;

    static {
        // common commands
        commands.put(NO_SUCH_COMMAND, new NoCommand());
        commands.put(EXIT, new ExitCommand());

        //user commands
        commands.put(SHOW_ALL_ITEMS_COMMAND, new ShowAllItemsCommand());

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
}
