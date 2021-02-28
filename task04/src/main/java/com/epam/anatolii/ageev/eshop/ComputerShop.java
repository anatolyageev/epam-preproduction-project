package com.epam.anatolii.ageev.eshop;

import com.epam.anatolii.ageev.eshop.admin_services.strategy.ItemBuildContainer;
import com.epam.anatolii.ageev.eshop.admin_services.strategy.Mode;
import com.epam.anatolii.ageev.eshop.repository.CartRepository;
import com.epam.anatolii.ageev.eshop.repository.CashRepository;
import com.epam.anatolii.ageev.eshop.repository.ItemsRepository;
import com.epam.anatolii.ageev.eshop.repository.OrderRepository;
import com.epam.anatolii.ageev.eshop.repository.impl.CartRepositoryImpl;
import com.epam.anatolii.ageev.eshop.repository.impl.CashRepositoryImpl;
import com.epam.anatolii.ageev.eshop.repository.impl.ItemsRepositoryImpl;
import com.epam.anatolii.ageev.eshop.repository.impl.OrderRepositoryImpl;
import com.epam.anatolii.ageev.eshop.servers.server_impl.HttpServer;
import com.epam.anatolii.ageev.eshop.servers.server_impl.TcpServer;
import com.epam.anatolii.ageev.eshop.services.CartService;
import com.epam.anatolii.ageev.eshop.services.CashService;
import com.epam.anatolii.ageev.eshop.services.ItemsService;
import com.epam.anatolii.ageev.eshop.services.OrderService;
import com.epam.anatolii.ageev.eshop.services.impl.CartServiceImpl;
import com.epam.anatolii.ageev.eshop.services.impl.CashServiceImpl;
import com.epam.anatolii.ageev.eshop.services.impl.ItemServiceImpl;
import com.epam.anatolii.ageev.eshop.services.impl.OrderServiceImpl;

import static com.epam.anatolii.ageev.eshop.constants.ServerConstants.HTTP_SERVER_PORT;
import static com.epam.anatolii.ageev.eshop.constants.ServerConstants.TCP_SERVER_PORT;

public class ComputerShop {
    private final Mode FILL_MODE;
    private final ItemsService itemsService;
    private final CartService cartService;
    private final OrderService orderService;
    private final CashService cashService;
    private final ItemBuildContainer itemBuildContainer;

    public ComputerShop(Mode mode) {
        FILL_MODE = mode;
        ItemsRepository itemsRepository = new ItemsRepositoryImpl();
        itemsService = new ItemServiceImpl(itemsRepository);
        CartRepository cartRepository = new CartRepositoryImpl();
        cartService = new CartServiceImpl(cartRepository);
        OrderRepository orderRepository = new OrderRepositoryImpl();
        orderService = new OrderServiceImpl(orderRepository);
        CashRepository cashRepository = new CashRepositoryImpl();
        cashService = new CashServiceImpl(cashRepository);
        itemBuildContainer = new ItemBuildContainer(mode.getImplementation());
    }

    public void serverStart() {
        new TcpServer(TCP_SERVER_PORT, itemsService).start();
        new HttpServer(HTTP_SERVER_PORT, itemsService).start();
    }

    public Mode getFILL_MODE() {
        return FILL_MODE;
    }

    public ItemsService getItemsService() {
        return itemsService;
    }

    public CartService getCartService() {
        return cartService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public CashService getCashService() {
        return this.cashService;
    }

    public ItemBuildContainer getItemBuildContainer() {
        return this.itemBuildContainer;
    }
}
