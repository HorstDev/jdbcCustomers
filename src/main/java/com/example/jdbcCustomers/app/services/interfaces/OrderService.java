package com.example.jdbcCustomers.app.services.interfaces;

import com.example.jdbcCustomers.app.models.Order;

public interface OrderService {
    Order findOrderById(long id);
    Long createOrder(Order order);
}
