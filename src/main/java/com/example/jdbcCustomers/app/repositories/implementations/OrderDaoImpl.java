package com.example.jdbcCustomers.app.repositories.implementations;

import com.example.jdbcCustomers.app.mappers.implementations.OrderRowMapper;
import com.example.jdbcCustomers.app.mappers.implementations.OrderWithCustomerRowMapper;
import com.example.jdbcCustomers.app.mappers.interfaces.OrderMapper;
import com.example.jdbcCustomers.app.models.Order;
import com.example.jdbcCustomers.app.repositories.interfaces.OrderDao;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public OrderDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Order findOrderById(long id) {
        String sql = "SELECT o.*, c.fio AS customerFio, c.phone AS customerPhone, " +
                "c.address AS customerAddress, c.created AS customerCreated " +
                "FROM \"order\" o " +
                "JOIN \"customer\" c ON o.customerId = c.id " +
                "WHERE o.id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(sql, params, new OrderWithCustomerRowMapper());
    }

    @Override
    public Long createOrder(Order order) {
        String sql = "INSERT INTO \"order\" (name, customerId) VALUES (:name, :customerId) RETURNING id";
        SqlParameterSource paramSource = new MapSqlParameterSource()
                .addValue("name", order.getName())
                .addValue("customerId", order.getCustomerId());
        return jdbcTemplate.queryForObject(sql, paramSource, Long.class);
    }
}
