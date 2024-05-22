package com.example.jdbcCustomers.app.repositories.implementations;

import com.example.jdbcCustomers.app.mappers.implementations.CustomerRowMapper;
import com.example.jdbcCustomers.app.models.Customer;
import com.example.jdbcCustomers.app.models.Order;
import com.example.jdbcCustomers.app.repositories.interfaces.CustomerDao;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    // Можно использовать и обычный JdbcTemplate, но с ним нужно указывать параметры в определенном порядке и держать
    // в голове этот порядок. А с NamedParameterJdbcTemplate параметры указываем в Map или SqlParameterSource
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CustomerDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Customer findCustomerById(long id) {
        String sql = "SELECT c.*, o.id AS order_id, o.name AS order_name, " +
                "o.created AS order_created, o.customerId AS customerId " +
                "FROM \"customer\" c " +
                "LEFT JOIN \"order\" o ON o.customerId = c.id " +
                "WHERE c.id = :id;";
        SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
        // Можно использовать лямбду (resultSet, rowNum) ->..., а можно использовать класс-маппер, реализующий RowMapper<T>
//        return jdbcTemplate.queryForObject(sql, paramSource, (resultSet, rowNum) -> {
//           Customer customer = new Customer();
//           customer.setId(resultSet.getLong("id"));
//           customer.setFio(resultSet.getString("fio"));
//           customer.setPhone(resultSet.getString("phone"));
//           customer.setAddress(resultSet.getString("address"));
//           customer.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
//           return customer;
//        });
        return jdbcTemplate.query(sql, paramSource, resultSet -> {
            Map<Long, Customer> customerWithOrders = new HashMap<>();
            while (resultSet.next()) {
                long customerId = resultSet.getLong("id");
                Customer customer = customerWithOrders.get(customerId);
                // Если customer еще не добавлен, добавляем
                if (customer == null) {
                    customer = Customer.builder()
                            .id(customerId)
                            .fio(resultSet.getString("fio"))
                            .phone(resultSet.getString("phone"))
                            .address(resultSet.getString("address"))
                            .created(resultSet.getTimestamp("created").toLocalDateTime())
                            .orders(new ArrayList<Order>())
                            .build();
                    customerWithOrders.put(customerId, customer);
                }

                long orderId = resultSet.getLong("order_id");
                if (orderId != 0) {
                    var order = Order.builder()
                            .id(resultSet.getLong("order_id"))
                            .name(resultSet.getString("order_name"))
                            .created(resultSet.getTimestamp("order_created").toLocalDateTime())
                            .customerId(resultSet.getLong("customerId"))
                            .build();
                    customer.getOrders().add(order);
                }
            }
            return customerWithOrders.get(id);
        });
    }

    public List<Customer> findAllCustomers() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, new CustomerRowMapper());
    }

    public Long createCustomer(Customer customer) {
        String sql = "INSERT INTO customer (fio, phone, address) VALUES (:fio, :phone, :address) RETURNING id";
//        Map<String, Object> params = new HashMap<>();
//        params.put("fio", customer.getFio());
//        params.put("phone", customer.getPhone());
//        params.put("address", customer.getAddress());
        SqlParameterSource paramSource = new MapSqlParameterSource()
                .addValue("fio", customer.getFio())
                .addValue("phone", customer.getPhone())
                .addValue("address", customer.getAddress());
        return jdbcTemplate.queryForObject(sql, paramSource, Long.class);
    }

    public void updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET fio = :fio, phone = :phone, address = :address WHERE id = :id";
        SqlParameterSource paramSource = new MapSqlParameterSource()
                .addValue("id", customer.getId())
                .addValue("fio", customer.getFio())
                .addValue("phone", customer.getPhone())
                .addValue("address", customer.getAddress());
        jdbcTemplate.update(sql, paramSource);
    }

    public void deleteCustomer(long id) {
        String sql = "DELETE FROM customer WHERE id = :id";
        SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(sql, paramSource);
    }
}
