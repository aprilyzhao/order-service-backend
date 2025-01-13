package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderItemsDto;
import com.example.orderservice.entity.OrderItems;

public class OrderItemsMapper {
    public static OrderItemsDto mapToOrderItemsDto(OrderItems orderItems){
        return new OrderItemsDto(
                orderItems.getId(),
                orderItems.getOrder(),
                orderItems.getProduct(),
                orderItems.getQuantity(),
                orderItems.getTotalPrice()
        );
    }

    public static OrderItems mapToOrderItems(OrderItemsDto orderItemsDto){
        return new OrderItems(
                orderItemsDto.getId(),
                orderItemsDto.getOrder(),
                orderItemsDto.getProduct(),
                orderItemsDto.getQuantity(),
                orderItemsDto.getTotalPrice()
        );
    }
}
