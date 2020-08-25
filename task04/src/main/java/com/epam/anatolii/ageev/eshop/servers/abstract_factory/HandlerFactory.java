package com.epam.anatolii.ageev.eshop.servers.abstract_factory;


import com.epam.anatolii.ageev.eshop.servers.hendler.ShopServerHandler;
import com.epam.anatolii.ageev.eshop.servers.server_impl.NetServer;
import com.epam.anatolii.ageev.eshop.services.ItemsService;

import java.net.Socket;

public interface HandlerFactory {

    ShopServerHandler createNewHandler(Socket socket, ItemsService itemsService);
}
