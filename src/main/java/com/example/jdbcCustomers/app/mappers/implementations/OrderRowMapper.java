package com.example.jdbcCustomers.app.mappers.implementations;

import com.example.jdbcCustomers.app.models.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    public Order mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Order.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .created(resultSet.getTimestamp("created").toLocalDateTime())
                .customerId(resultSet.getLong("customerId"))
                .build();
    }
}
