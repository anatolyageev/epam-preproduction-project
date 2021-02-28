package com.epam.anatolii.ageev.eshop.servers.command;

import com.epam.anatolii.ageev.eshop.servers.command.impl.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.epam.anatolii.ageev.eshop.constants.ServerConstants.*;

public class ServerCommandContaiter {
    private static Map<String, ServerCommand> serverCommands = new LinkedHashMap<>();

    static {
        serverCommands.put(GET_COUNT_COMMAND, new TcpServerGetCountCommand());
        serverCommands.put(GET_ITEM_COMMAND, new TcpServerGetItemCommand());
        serverCommands.put(GET_HTTP_COUNT_COMMAND, new HttpServerGetCountCommand());
        serverCommands.put(GET_HTTP_ITEM_COMMAND, new HttpServerGetItemCommand());
        serverCommands.put(NO_SUCH_COMMAND, new NoSuchCommand());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName name of the command.
     * @return ServerCommand object.
     */
    public ServerCommand getServerCommand(String commandName) {
        if (commandName == null || !serverCommands.containsKey(commandName)) {
            return serverCommands.get(NO_SUCH_COMMAND);
        }
        return serverCommands.get(commandName);
    }
}
