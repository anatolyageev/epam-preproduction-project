package com.epam.anatolii.ageev.eshop.servers.hendler;

import com.epam.anatolii.ageev.eshop.servers.command.ServerCommand;
import com.epam.anatolii.ageev.eshop.servers.command.ServerCommandContaiter;
import com.epam.anatolii.ageev.eshop.services.ItemsService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.epam.anatolii.ageev.eshop.constants.ServerConstants.NO_SUCH_COMMAND;

/**
 * Class for handle client requests for TCP Server.
 */
public class ShopTcpHandler extends ShopServerHandler {
    private static final String REGEX = "get\\sitem=\\d+|get\\scount";

    public ShopTcpHandler(Socket socket, ItemsService itemsService) {
        this.socket = socket;
        this.itemsService = itemsService;
        serverCommandContaiter = new ServerCommandContaiter();
    }

    public void run() {
        String request;
        try (PrintStream os = new PrintStream(socket.getOutputStream());
             BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            request = is.readLine();
            ServerCommand serverCommand;
            String resultFromCommand;
            Pattern pattern = Pattern.compile(REGEX);
            Matcher matcher = pattern.matcher(request);
            if (matcher.find()) {
                serverCommand = serverCommandContaiter.getServerCommand(request.split("=")[0]);
                resultFromCommand = serverCommand.execute(request, itemsService);
            } else {
                resultFromCommand = NO_SUCH_COMMAND;
            }
            os.println(resultFromCommand);
            socket.close();
        } catch (IOException e) {
            System.out.println("Disconnect");
        } finally {
            disconnect();
        }
    }
}
