package com.example.jdbcCustomers.app.mappers.interfaces;

import com.example.jdbcCustomers.app.models.Order;
import com.example.jdbcCustomers.app.viewModels.order.AddOrderViewModel;

public interface OrderMapper {
    Order mapToDomain(AddOrderViewModel addOrderViewModel);
}
