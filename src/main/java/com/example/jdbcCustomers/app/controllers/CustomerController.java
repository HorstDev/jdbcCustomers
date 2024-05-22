package com.example.jdbcCustomers.app.controllers;

import com.example.jdbcCustomers.app.mappers.interfaces.CustomerMapper;
import com.example.jdbcCustomers.app.models.Customer;
import com.example.jdbcCustomers.app.services.interfaces.CustomerService;
import com.example.jdbcCustomers.app.viewModels.customer.AddCustomerViewModel;
import com.example.jdbcCustomers.app.viewModels.customer.CustomerViewModel;
import com.example.jdbcCustomers.app.viewModels.customer.CustomerWithOrdersViewModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping("{id}")
    public CustomerWithOrdersViewModel getCustomer(@PathVariable int id) {
        Customer customer = customerService.findCustomerById(id);
        return customerMapper.mapToViewModelWithOrders(customer);
    }

    @GetMapping
    public List<CustomerViewModel> getAllCustomers() {
        List<Customer> customers = customerService.findAllCustomers();
        return customers.stream().map(customerMapper::mapToViewModel)
                .collect(Collectors.toList());
    }

    @PostMapping
    public long createCustomer(@RequestBody AddCustomerViewModel customerVm) {
        Customer customer = customerMapper.mapToDomain(customerVm);
        return customerService.createCustomer(customer);
    }

    @PutMapping
    public void updateCustomer(@RequestBody CustomerViewModel customerVm) {
        Customer customer = customerMapper.mapToDomain(customerVm);
        customerService.updateCustomer(customer);
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable long id) {
        customerService.deleteCustomer(id);
    }
}
