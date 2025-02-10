package com.example.orderservice.service.impl;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.exception.ResourceNotFoundException;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order  = OrderMapper.mapToOrder(orderDto);
        Order savedOrder = orderRepository.save(order);
        return OrderMapper.mapToOrderDto(savedOrder);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().
                map(OrderMapper::mapToOrderDto)
                .collect(Collectors.toList());
    }


    @Override
    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Order is not exists with given id." + id));
        return OrderMapper.mapToOrderDto(order);
    }

    @Override
    public OrderDto getOrderByUserId(Long userId) {
        Optional<Order> optionalOrder = orderRepository.findFirstByUser_IdOrderByIdDesc(userId);
        if ( optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return OrderMapper.mapToOrderDto(order);
        }
        else {
            throw new ResourceNotFoundException("Order not found for user id: " + userId);
        }
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto updatedOrder) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Order is not exists with given id." + id));

        order.setOrderItems(updatedOrder.getOrderItems());
        order.setUser(updatedOrder.getUser());
        order.setTotalPrice(updatedOrder.getTotalPrice());

        Order updatedOrderObj = orderRepository.save(order);

        return OrderMapper.mapToOrderDto(updatedOrderObj);

    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Order is not exists with given id." + id));
        orderRepository.deleteById(id);
    }

}
