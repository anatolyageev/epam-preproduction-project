package com.epam.anatolii.ageev.eshop.servers.server_impl;

import com.epam.anatolii.ageev.eshop.servers.abstract_factory.impl.HttpHandleFactory;
import com.epam.anatolii.ageev.eshop.services.ItemsService;

/**
 * Class for create HTTP Server.
 */
public class HttpServer extends NetServer {
    public HttpServer(int port, ItemsService itemsService) {
        super(port, itemsService);
        handlerFactory = new HttpHandleFactory();
    }
}
