package com.example.orderservice.service;

import com.example.orderservice.dto.OrderItemsDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderItemsService {

    OrderItemsDto addItemsToCart(Long orderId, Long productId, Integer quantity, Long userId);

    OrderItemsDto getOrderItemsById(Long id);

    List<OrderItemsDto> getAllOrderItems();

    OrderItemsDto updateOrderItems(Long id, OrderItemsDto updatedOrderItems);

    void deleteOrderItems(Long id);

    List<OrderItemsDto> getOrderItemsByOrderId(Long orderId);
}
