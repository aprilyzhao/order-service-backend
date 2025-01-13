package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderItemsDto;
import com.example.orderservice.service.OrderItemsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orderitems")
public class OrderItemsController {

    private OrderItemsService orderItemsService;

    //Build Add OrderItems REST API
    @PostMapping
    public ResponseEntity<OrderItemsDto> createOrderItems(@RequestBody OrderItemsDto orderItemsDto){
        OrderItemsDto savedorderDto = orderItemsService.createOrderItems(orderItemsDto);
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
        List<OrderItemsDto> orderItemsDtosList= orderItemsService.getAllOrderItems();
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
