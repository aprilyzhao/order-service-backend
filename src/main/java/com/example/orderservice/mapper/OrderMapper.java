package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Order;

public class OrderMapper {
    public static OrderDto mapToOrderDto(Order order){
        return new OrderDto(
                order.getId(),
                order.getUser(),
                order.getOrderItems(),
                order.getTotalPrice()
        );
    }

    public static Order mapToOrder(OrderDto orderDto){
        return new Order(
                orderDto.getId(),
                orderDto.getUser(),
                orderDto.getOrderItems(),
                orderDto.getTotalPrice()
        );
    }
}
