package com.example.orderservice.repository;

import com.example.orderservice.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
    List<OrderItems> findByOrder_Id(Long orderId);
}
