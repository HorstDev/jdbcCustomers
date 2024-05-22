package com.example.jdbcCustomers.app.mappers.implementations;

import com.example.jdbcCustomers.app.models.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var customer = new Customer();
        customer.setId(resultSet.getLong("id"));
        customer.setFio(resultSet.getString("fio"));
        customer.setPhone(resultSet.getString("phone"));
        customer.setAddress(resultSet.getString("address"));
        customer.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
        return customer;
    }
}
