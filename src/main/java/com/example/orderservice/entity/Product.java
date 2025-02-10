package com.example.orderservice.entity;


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
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="product_name", nullable = false)
    private String name;

    @Column(name="product_price", nullable = false)
    private Double price;

    @Column(nullable = false)
    private String imageUrl;

    @Column
    private Boolean newArrival;

    @Column
    @ManyToMany
    private List<Tag> tags;

    @Column
    private String description;

}
