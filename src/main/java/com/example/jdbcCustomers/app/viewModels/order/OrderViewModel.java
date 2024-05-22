package com.example.jdbcCustomers.app.viewModels.order;

import com.example.jdbcCustomers.app.viewModels.customer.CustomerViewModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderViewModel {
    private long id;
    private String name;
    private LocalDateTime created;
    private CustomerViewModel customer;
}
