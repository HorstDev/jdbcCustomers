package com.example.jdbcCustomers.app.viewModels.customer;

import com.example.jdbcCustomers.app.viewModels.order.OrderViewModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerWithOrdersViewModel {
    private long id;
    private String fio;
    private String phone;
    private String address;
    private LocalDateTime created;
    private List<OrderViewModel> orders;
}
