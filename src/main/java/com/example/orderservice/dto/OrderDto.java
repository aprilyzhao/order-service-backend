package com.example.orderservice.dto;

import com.example.orderservice.entity.OrderItems;
import com.example.orderservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private User user;
    private List<OrderItems> orderItems;
    private Double totalPrice;
}
