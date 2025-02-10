package com.example.orderservice.service.impl;

import com.example.orderservice.dto.OrderItemsDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItems;
import com.example.orderservice.entity.Product;
import com.example.orderservice.entity.User;
import com.example.orderservice.exception.ResourceNotFoundException;
import com.example.orderservice.mapper.OrderItemsMapper;
import com.example.orderservice.repository.OrderItemsRepository;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.repository.ProductRepository;
import com.example.orderservice.repository.UserRepository;
import com.example.orderservice.service.OrderItemsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderItemsServiceImpl implements OrderItemsService {

    private final OrderItemsRepository orderItemsRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderItemsDto addItemsToCart(Long orderId, Long productId, Integer quantity, Long userId) {

        Order order = orderRepository.findById(orderId).orElseGet(() -> {
                    Order newOrder = new Order();
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
                    newOrder.setUser(user);
                    newOrder.setStatus("Pending");
                    newOrder.setTotalPrice(0.0);
                    return orderRepository.save(newOrder);
                });

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));

            Optional<OrderItems> existingItem = order.getOrderItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            // if order_item exists, update quantity via updateOrderItems()
            OrderItems existingOrderItem = existingItem.get();
            existingOrderItem.setQuantity(existingOrderItem.getQuantity() + quantity);
            existingOrderItem.setTotalPrice(existingOrderItem.getQuantity() * product.getPrice());
            OrderItems updatedOrderItem = orderItemsRepository.save(existingOrderItem);
            return OrderItemsMapper.mapToOrderItemsDto(updatedOrderItem);

        } else {
            //if item does not exist, then create a new OrderItems
            OrderItems newOrderItem = new OrderItems();
            newOrderItem.setOrder(order);
            newOrderItem.setProduct(product);
            newOrderItem.setQuantity(quantity);
            newOrderItem.setTotalPrice(quantity * product.getPrice());
            OrderItems savedOrderItems = orderItemsRepository.save(newOrderItem);
            return OrderItemsMapper.mapToOrderItemsDto(savedOrderItems);
        }
    }

    @Override
    public OrderItemsDto updateOrderItems(Long id, OrderItemsDto updatedOrderItems) {
        OrderItems orderItems = orderItemsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order Item does not exist with given id: " + id));

        orderItems.setQuantity(orderItems.getQuantity() + updatedOrderItems.getQuantity());
        orderItems.setTotalPrice(orderItems.getQuantity() * updatedOrderItems.getProduct().getPrice());

        OrderItems updatedOrderItemsObj = orderItemsRepository.save(orderItems);
        // if change to 0, delete the orderitems directly
        if (orderItems.getQuantity() == 0){
            deleteOrderItems(id);
        }
        return OrderItemsMapper.mapToOrderItemsDto(updatedOrderItemsObj);
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
    public void deleteOrderItems(Long id) {
        OrderItems orderItems = orderItemsRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Order Items is not exists with given id." + id));
        orderItemsRepository.deleteById(id);
    }

    @Override
    public List<OrderItemsDto> getOrderItemsByOrderId(Long orderId) {
        System.out.println("Fetching order items for order ID: " + orderId);
        List<OrderItems> orderItemsList = orderItemsRepository.findByOrder_Id(orderId);
        if (orderItemsList.isEmpty()) {
            System.out.println("⚠️ No order items found for order ID: " + orderId);
        }
        return orderItemsList.stream()
                .map((orderItems)->OrderItemsMapper.mapToOrderItemsDto(orderItems))
                .collect(Collectors.toList());
    }
}
