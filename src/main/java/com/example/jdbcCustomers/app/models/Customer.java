package com.example.jdbcCustomers.app.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private long id;
    private String fio;
    private String phone;
    private String address;
    private LocalDateTime created;
    List<Order> orders = new ArrayList<>();
}
