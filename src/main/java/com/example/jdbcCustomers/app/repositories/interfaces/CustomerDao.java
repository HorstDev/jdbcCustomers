package com.example.jdbcCustomers.app.repositories.interfaces;

import com.example.jdbcCustomers.app.models.Customer;

import java.util.List;

public interface CustomerDao {
    Customer findCustomerById(long id);
    List<Customer> findAllCustomers();
    Long createCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(long id);
}
