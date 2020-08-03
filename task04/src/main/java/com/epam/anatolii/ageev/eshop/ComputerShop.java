package com.epam.anatolii.ageev.eshop;

import com.epam.anatolii.ageev.eshop.repository.CartRepository;
import com.epam.anatolii.ageev.eshop.repository.CashRepository;
import com.epam.anatolii.ageev.eshop.repository.ItemsRepository;
import com.epam.anatolii.ageev.eshop.repository.OrderRepository;
import com.epam.anatolii.ageev.eshop.repository.impl.CartRepositoryImpl;
import com.epam.anatolii.ageev.eshop.repository.impl.CashRepositoryImpl;
import com.epam.anatolii.ageev.eshop.repository.impl.ItemsRepositoryImpl;
import com.epam.anatolii.ageev.eshop.repository.impl.OrderRepositoryImpl;
import com.epam.anatolii.ageev.eshop.services.CartService;
import com.epam.anatolii.ageev.eshop.services.CashService;
import com.epam.anatolii.ageev.eshop.services.ItemsService;
import com.epam.anatolii.ageev.eshop.services.OrderService;
import com.epam.anatolii.ageev.eshop.services.impl.CartServiceImpl;
import com.epam.anatolii.ageev.eshop.services.impl.CashServiceImpl;
import com.epam.anatolii.ageev.eshop.services.impl.ItemServiceImpl;
import com.epam.anatolii.ageev.eshop.services.impl.OrderServiceImpl;

public class ComputerShop {
    private ItemsRepository itemsRepository;
    private CartRepository cartRepository;
    private OrderRepository orderRepository;
    private CashRepository cashRepository;
    private ItemsService itemsService;
    private CartService cartService;
    private OrderService orderService;
    private CashService cashService;

    public ComputerShop(){
        this.itemsRepository = new ItemsRepositoryImpl();
        this.itemsService = new ItemServiceImpl(itemsRepository);
        this.cartRepository = new CartRepositoryImpl();
        this.cartService = new CartServiceImpl(cartRepository);
        this.orderRepository = new OrderRepositoryImpl();
        this.orderService = new OrderServiceImpl(orderRepository);
        this.cashRepository = new CashRepositoryImpl();
        this.cashService = new CashServiceImpl(cashRepository);
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

    public CashService getCashService(){
        return this.cashService;
    }
}
