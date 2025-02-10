package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(Long id);

    OrderDto getOrderByUserId(Long userId);

    OrderDto updateOrder(Long id, OrderDto updatedOrder);

    void deleteOrder(Long id);

}
