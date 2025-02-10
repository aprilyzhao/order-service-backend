package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderItemsDto;
import com.example.orderservice.service.OrderItemsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orderitems")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderItemsController {

    @Autowired
    private final OrderItemsService orderItemsService;

    //Build Add OrderItems REST API
    @PostMapping("/user/{userId}")
    public ResponseEntity<OrderItemsDto> addItemsToCart(@PathVariable("userId") Long userId,@RequestBody OrderItemsDto newAddedItems){
        OrderItemsDto savedorderDto = orderItemsService.addItemsToCart(newAddedItems.getOrder().getId(), newAddedItems.getProduct().getId(), newAddedItems.getQuantity(), userId);
        return new ResponseEntity<>(savedorderDto, HttpStatus.CREATED);
    }

    //Build GET OrderItems REST API
    @GetMapping("{id}")
    public ResponseEntity<OrderItemsDto> getOrderItemsDtoById(@PathVariable("id") Long id){
        OrderItemsDto orderItemsDto = orderItemsService.getOrderItemsById(id);
        return ResponseEntity.ok(orderItemsDto);
    }

    //Build Get All OrderItems REST API
    @GetMapping
    public ResponseEntity<List<OrderItemsDto>> getAllOrderItems(){
        List<OrderItemsDto> orderItemsDtosList = orderItemsService.getAllOrderItems();
        return ResponseEntity.ok(orderItemsDtosList);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItemsDto>> getOrderItemsByOrderId(@PathVariable("orderId")Long orderId){
        List<OrderItemsDto> orderItemsDtosList = orderItemsService.getOrderItemsByOrderId(orderId);
        return ResponseEntity.ok(orderItemsDtosList);
    }

    //Build Update OrderItems REST API
    @PutMapping("{id}")
    public ResponseEntity<OrderItemsDto> updateOrderItems(@PathVariable("id") Long id, @RequestBody OrderItemsDto updatedOrderItems){
        OrderItemsDto orderItemsDto = orderItemsService.updateOrderItems(id, updatedOrderItems);
        return ResponseEntity.ok(orderItemsDto);
    }

    //Build Delete OrderItems REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrderItems(@PathVariable("id") Long id){
        orderItemsService.deleteOrderItems(id);
        return ResponseEntity.ok("OrderItems deleted successfully");
    }
}
