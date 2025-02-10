package com.example.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //JoinColumn means user_id in orders_table is a foreign key
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //One-to-many relationship does not store as column in parent table
    @OneToMany
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private List<OrderItems> orderItems;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name="order_status")
    private String status;

    public void setTotalPrice() {
        if(orderItems != null) {
            this.totalPrice = orderItems.stream()
                    .mapToDouble(OrderItems::getTotalPrice)
                    .sum();
        }
    }

}
