package com.example.jdbcCustomers.app.repositories.interfaces;

import com.example.jdbcCustomers.app.models.Order;

import java.util.List;

public interface OrderDao {
    Order findOrderById(long id);
//    List<Customer> findAllCustomers();
    Long createOrder(Order order);
//    void updateCustomer(Customer customer);
//    void deleteCustomer(long id);
}
