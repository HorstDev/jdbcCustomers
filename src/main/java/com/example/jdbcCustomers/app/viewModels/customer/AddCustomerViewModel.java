package com.example.jdbcCustomers.app.viewModels.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCustomerViewModel {
    private String fio;
    private String phone;
    private String address;
}
