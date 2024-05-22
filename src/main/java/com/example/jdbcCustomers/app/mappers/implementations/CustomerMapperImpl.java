package com.example.jdbcCustomers.app.mappers.implementations;

import com.example.jdbcCustomers.app.mappers.interfaces.CustomerMapper;
import com.example.jdbcCustomers.app.models.Customer;
import com.example.jdbcCustomers.app.viewModels.customer.AddCustomerViewModel;
import com.example.jdbcCustomers.app.viewModels.customer.CustomerViewModel;
import com.example.jdbcCustomers.app.viewModels.customer.CustomerWithOrdersViewModel;
import com.example.jdbcCustomers.app.viewModels.order.OrderViewModel;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerMapperImpl implements CustomerMapper {
    public CustomerViewModel mapToViewModel(Customer customer) {
        return CustomerViewModel.builder()
                    .id(customer.getId())
                    .fio(customer.getFio())
                    .phone(customer.getPhone())
                    .address(customer.getAddress())
                    .created(customer.getCreated())
                    .build();
    }

    public CustomerWithOrdersViewModel mapToViewModelWithOrders(Customer customer) {
        return CustomerWithOrdersViewModel.builder()
                .id(customer.getId())
                .fio(customer.getFio())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .created(customer.getCreated())
                .orders(customer.getOrders().stream().map(order ->
                        OrderViewModel.builder()
                                .id(order.getId())
                                .name(order.getName())
                                .created(order.getCreated())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public Customer mapToDomain(AddCustomerViewModel addCustomerViewModel) {
        return Customer.builder()
                .fio(addCustomerViewModel.getFio())
                .phone(addCustomerViewModel.getPhone())
                .address(addCustomerViewModel.getAddress())
                .build();
    }

    public Customer mapToDomain(CustomerViewModel customerViewModel) {
        return Customer.builder()
                .id(customerViewModel.getId())
                .fio(customerViewModel.getFio())
                .phone(customerViewModel.getPhone())
                .address(customerViewModel.getAddress())
                .created(customerViewModel.getCreated())
                .build();
    }
}
