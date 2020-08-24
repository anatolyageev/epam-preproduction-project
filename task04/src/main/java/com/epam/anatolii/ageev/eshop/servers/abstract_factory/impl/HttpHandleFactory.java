package com.epam.anatolii.ageev.eshop.servers.abstract_factory.impl;

import com.epam.anatolii.ageev.eshop.servers.abstract_factory.HandlerFactory;
import com.epam.anatolii.ageev.eshop.servers.hendler.ShopHttpHandler;
import com.epam.anatolii.ageev.eshop.servers.hendler.ShopServerHandler;
import com.epam.anatolii.ageev.eshop.services.ItemsService;

import java.net.Socket;

public class HttpHandleFactory implements HandlerFactory {
    @Override
    public ShopServerHandler createNewHandler(Socket socket, ItemsService itemsService) {
        return new ShopHttpHandler(socket, itemsService);
    }
}
