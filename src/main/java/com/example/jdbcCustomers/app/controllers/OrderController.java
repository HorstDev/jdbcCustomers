package com.example.jdbcCustomers.app.controllers;

import com.example.jdbcCustomers.app.mappers.interfaces.OrderMapper;
import com.example.jdbcCustomers.app.models.Customer;
import com.example.jdbcCustomers.app.models.Order;
import com.example.jdbcCustomers.app.services.interfaces.OrderService;
import com.example.jdbcCustomers.app.viewModels.order.AddOrderViewModel;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

//    @GetMapping
//    public List<OrderViewModel> getOrders() {
//
//    }
    @GetMapping("{id}")
    public Order getOrder(@PathVariable long id) {
        return orderService.findOrderById(id);
    }

    @PostMapping("{customerId}")
    public long createOrder(@PathVariable long customerId, @RequestBody AddOrderViewModel orderVm) {
        Order order = orderMapper.mapToDomain(orderVm);
        order.setCustomerId(customerId);
        return orderService.createOrder(order);
    }
}
