package com.example.jdbcCustomers.app.viewModels.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerViewModel {
    private long id;
    private String fio;
    private String phone;
    private String address;
    private LocalDateTime created;
}
