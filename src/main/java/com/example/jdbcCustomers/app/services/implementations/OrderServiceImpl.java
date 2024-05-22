package com.example.jdbcCustomers.app.services.implementations;

import com.example.jdbcCustomers.app.models.Order;
import com.example.jdbcCustomers.app.repositories.interfaces.OrderDao;
import com.example.jdbcCustomers.app.services.interfaces.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order findOrderById(long id) {
        return orderDao.findOrderById(id);
    }

    @Override
    public Long createOrder(Order order) {
        return orderDao.createOrder(order);
    }
}
