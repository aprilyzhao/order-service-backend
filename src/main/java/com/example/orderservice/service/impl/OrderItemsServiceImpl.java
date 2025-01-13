package com.example.orderservice.service.impl;

import com.example.orderservice.dto.OrderItemsDto;
import com.example.orderservice.entity.OrderItems;
import com.example.orderservice.exception.ResourceNotFoundException;
import com.example.orderservice.mapper.OrderItemsMapper;
import com.example.orderservice.repository.OrderItemsRepository;
import com.example.orderservice.service.OrderItemsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderItemsServiceImpl implements OrderItemsService {

    private OrderItemsRepository orderItemsRepository;

    @Override
    public OrderItemsDto createOrderItems(OrderItemsDto orderItemsDto) {
        OrderItems orderItems = OrderItemsMapper.mapToOrderItems(orderItemsDto);
        OrderItems savedOrderItems = orderItemsRepository.save(orderItems);
        return OrderItemsMapper.mapToOrderItemsDto(savedOrderItems);
    }

    @Override
    public OrderItemsDto getOrderItemsById(Long id) {
        OrderItems orderItems = orderItemsRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Order Items is not exists with given id." + id));
        return OrderItemsMapper.mapToOrderItemsDto(orderItems);
    }

    @Override
    public List<OrderItemsDto> getAllOrderItems() {
        List<OrderItems> orderItemsList = orderItemsRepository.findAll();
        return orderItemsList.stream()
                .map((orderItems)->OrderItemsMapper.mapToOrderItemsDto(orderItems))
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemsDto updateOrderItems(Long id, OrderItemsDto updatedOrderItems) {
        OrderItems orderItems = orderItemsRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Order Items is not exists with given id." + id));

        orderItems.setOrder(updatedOrderItems.getOrder());
        orderItems.setProduct(updatedOrderItems.getProduct());
        orderItems.setQuantity(updatedOrderItems.getQuantity());
        orderItems.setTotalPrice(updatedOrderItems.getTotalPrice());

        OrderItems updatedOrderItemsObj = orderItemsRepository.save(orderItems);

        return OrderItemsMapper.mapToOrderItemsDto(updatedOrderItemsObj);
    }

    @Override
    public void deleteOrderItems(Long id) {
        OrderItems orderItems = orderItemsRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Order Items is not exists with given id." + id));

        orderItemsRepository.deleteById(id);
    }
}
