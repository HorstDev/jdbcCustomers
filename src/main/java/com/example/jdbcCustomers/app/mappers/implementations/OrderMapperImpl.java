package com.example.jdbcCustomers.app.mappers.implementations;

import com.example.jdbcCustomers.app.mappers.interfaces.OrderMapper;
import com.example.jdbcCustomers.app.models.Order;
import com.example.jdbcCustomers.app.viewModels.order.AddOrderViewModel;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {
    @Override
    public Order mapToDomain(AddOrderViewModel addOrderViewModel) {
        return Order.builder()
                .name(addOrderViewModel.getName())
                .build();
    }
}
