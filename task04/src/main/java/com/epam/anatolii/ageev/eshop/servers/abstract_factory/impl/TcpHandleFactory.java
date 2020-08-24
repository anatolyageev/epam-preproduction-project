package com.epam.anatolii.ageev.eshop.servers.abstract_factory.impl;

import com.epam.anatolii.ageev.eshop.servers.abstract_factory.HandlerFactory;
import com.epam.anatolii.ageev.eshop.servers.hendler.ShopServerHandler;
import com.epam.anatolii.ageev.eshop.servers.hendler.ShopTcpHandler;
import com.epam.anatolii.ageev.eshop.services.ItemsService;

import java.net.Socket;

public class TcpHandleFactory implements HandlerFactory {
    @Override
    public ShopServerHandler createNewHandler(Socket socket, ItemsService itemsService) {
        return new ShopTcpHandler(socket, itemsService);
    }
}
