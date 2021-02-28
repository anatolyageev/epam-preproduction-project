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

import static com.epam.anatolii.ageev.eshop.constants.ServerConstants.*;

/**
 * Class for handle client requests for HTTP Server.
 */
public class ShopHttpHandler extends ShopServerHandler {

    private static final String REGEX = "GET\\s/shop/(item\\?get_info=\\d+|count)\\sHTTP/1.1";

    public ShopHttpHandler(Socket socket, ItemsService itemsService) {
        this.socket = socket;
        this.itemsService = itemsService;
        serverCommandContaiter = new ServerCommandContaiter();
    }

    @Override
    public void run() {
        String request;
        try (PrintStream os = new PrintStream(socket.getOutputStream());
             BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            request = is.readLine();
            int RequestStatus = STATUS_CODE_SUCCESS;
            String resultFromCommand;
            ServerCommand serverCommand;
            Pattern pattern = Pattern.compile(REGEX);
            Matcher matcher = pattern.matcher(request);
            if (matcher.find()) {
                serverCommand = serverCommandContaiter.getServerCommand(matcher.group(1).split("\\?")[0]);
                resultFromCommand = serverCommand.execute(matcher.group(1), itemsService);
            } else {
                RequestStatus = STATUS_CODE_BAD_REQUEST;
                resultFromCommand = NO_SUCH_COMMAND;
            }
            os.println(httpResponse(RequestStatus, resultFromCommand));
            socket.close();
        } catch (IOException e) {
            System.out.println("Disconnect");
        } finally {
            disconnect();
        }
    }

    private String httpResponse(int responceStatus, String result) {
        StringBuilder resultString = new StringBuilder();
        resultString.append("HTTP/1.1 ").append(responceStatus).append(System.lineSeparator())
                .append("Content-Type: text/html; charset=UTF-8").append(System.lineSeparator())
                .append("Connection: close").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(result).append(System.lineSeparator());
        return resultString.toString();
    }
}
