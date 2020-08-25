package com.epam.anatolii.ageev.eshop.servers.hendler;

import com.epam.anatolii.ageev.eshop.servers.command.ServerCommandContaiter;
import com.epam.anatolii.ageev.eshop.services.ItemsService;

import java.io.IOException;
import java.net.Socket;

/**
 * Parent class for handlers
 */

public abstract class ShopServerHandler implements Runnable {
    protected ItemsService itemsService;
    protected Socket socket;
    protected ServerCommandContaiter serverCommandContaiter;


    protected void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
