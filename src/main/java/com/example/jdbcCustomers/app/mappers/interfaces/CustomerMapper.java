package com.example.jdbcCustomers.app.mappers.interfaces;

import com.example.jdbcCustomers.app.models.Customer;
import com.example.jdbcCustomers.app.viewModels.customer.AddCustomerViewModel;
import com.example.jdbcCustomers.app.viewModels.customer.CustomerViewModel;
import com.example.jdbcCustomers.app.viewModels.customer.CustomerWithOrdersViewModel;

public interface CustomerMapper {
    CustomerViewModel mapToViewModel(Customer customer);
    CustomerWithOrdersViewModel mapToViewModelWithOrders(Customer customer);
    Customer mapToDomain(AddCustomerViewModel addCustomerViewModel);
    Customer mapToDomain(CustomerViewModel customerViewModel);
}
