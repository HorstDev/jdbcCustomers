package com.example.jdbcCustomers.app.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private long id;
    private String name;
    private LocalDateTime created;
    private long customerId;
    private Customer customer;
}
