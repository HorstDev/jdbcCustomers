package com.example.jdbcCustomers.app.mappers.implementations;

import com.example.jdbcCustomers.app.models.Customer;
import com.example.jdbcCustomers.app.models.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderWithCustomerRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var customer = Customer.builder()
                .id(resultSet.getLong("customerId"))
                .fio(resultSet.getString("customerFio"))
                .phone(resultSet.getString("customerPhone"))
                .address(resultSet.getString("customerAddress"))
                .created(resultSet.getTimestamp("customerCreated").toLocalDateTime())
                .build();

        return Order.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .created(resultSet.getTimestamp("created").toLocalDateTime())
                .customerId(resultSet.getLong("customerId"))
                .customer(customer)
                .build();
    }
}
