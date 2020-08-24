package com.epam.anatolii.ageev.eshop.servers.server_impl;

import com.epam.anatolii.ageev.eshop.servers.abstract_factory.impl.TcpHandleFactory;
import com.epam.anatolii.ageev.eshop.services.ItemsService;

/**
 * Class for create TCP Server.
 */
public class TcpServer extends NetServer {
    public TcpServer(int port, ItemsService itemsService) {
        super(port, itemsService);
        handlerFactory = new TcpHandleFactory();
    }
}
