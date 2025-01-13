package com.example.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //many items can belong to one order
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    //Many-to-one means many order_items can have a certain product
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_price")
    private Double totalPrice;

    private void setTotalPrice() {
        if(product != null && quantity != null) {
            this.totalPrice = product.getPrice() * quantity;
        }
    }
}
