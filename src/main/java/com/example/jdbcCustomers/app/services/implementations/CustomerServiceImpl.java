package com.example.jdbcCustomers.app.services.implementations;

import com.example.jdbcCustomers.app.models.Customer;
import com.example.jdbcCustomers.app.repositories.interfaces.CustomerDao;
import com.example.jdbcCustomers.app.services.interfaces.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Customer findCustomerById(long id) {
        return customerDao.findCustomerById(id);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerDao.findAllCustomers();
    }

    @Override
    public Long createCustomer(Customer customer) {
        return customerDao.createCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDao.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(long id) {
        customerDao.deleteCustomer(id);
    }
}
