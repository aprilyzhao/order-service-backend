package com.example.orderservice.dto;

import com.example.orderservice.entity.Tag;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
    private String imageUrl;
    private Boolean newArrival;
    private List<Tag> tags;
    private String description;
}
