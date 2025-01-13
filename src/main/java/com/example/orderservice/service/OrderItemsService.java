package com.example.orderservice.service;

import com.example.orderservice.dto.OrderItemsDto;

import java.util.List;

public interface OrderItemsService {

    OrderItemsDto createOrderItems(OrderItemsDto orderItemsDto);

    OrderItemsDto getOrderItemsById(Long id);

    List<OrderItemsDto> getAllOrderItems();

    OrderItemsDto updateOrderItems(Long id, OrderItemsDto updatedOrderItems);

    void deleteOrderItems(Long id);
}
