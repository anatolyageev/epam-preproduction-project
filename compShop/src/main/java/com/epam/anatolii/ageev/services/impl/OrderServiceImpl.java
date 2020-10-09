package com.epam.anatolii.ageev.services.impl;

import com.epam.anatolii.ageev.domain.Order;
import com.epam.anatolii.ageev.repository.OrderRepository;
import com.epam.anatolii.ageev.repository.ProductRepository;
import com.epam.anatolii.ageev.repository.transaction.TransactionManager;
import com.epam.anatolii.ageev.repository.transaction.impl.TransactionManagerImpl;
import com.epam.anatolii.ageev.services.OrderService;
import com.epam.anatolii.ageev.web.servlets.OrderServlet;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class OrderServiceImpl implements OrderService {
    final static Logger LOG = Logger.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final TransactionManager transactionManager;

    public OrderServiceImpl(OrderRepository orderRepository, DataSource dataSource) {
        this.transactionManager = new TransactionManagerImpl(dataSource);
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        LOG.debug("order: " + order);
        return transactionManager.executeDmlTransaction(()->orderRepository.createOrder(order));
    }
}
