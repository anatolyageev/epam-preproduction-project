package com.epam.anatolii.ageev.eshop.servers.server_impl;

import com.epam.anatolii.ageev.eshop.servers.abstract_factory.HandlerFactory;
import com.epam.anatolii.ageev.eshop.services.ItemsService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Parent class for server
 */

public abstract class NetServer extends Thread {
    protected ItemsService itemsService;
    protected HandlerFactory handlerFactory;
    protected int port;

    public NetServer(int port, ItemsService itemsService) {
        this.port = port;
        this.itemsService = itemsService;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println((char) 27 + "[34m" + this.getClass().getSimpleName() + " Started! " + (char)27 + "[0m");
            while (true) {
                Socket sock = serverSocket.accept();
                new Thread(handlerFactory.createNewHandler(sock, itemsService)).start();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
