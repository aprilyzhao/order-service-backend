package com.example.orderservice.controller;

import com.example.orderservice.dto.ProductDto;
import com.example.orderservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    //Build Add Product REST API
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto savedProduct = productService.createProduct(productDto);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    //Build GET Product REST API
    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productid) {
        ProductDto productDto = productService.getProductByid(productid);
        return ResponseEntity.ok(productDto);
    }

    //Build GET All Product REST API
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProduct(){
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    //Build Update Product REST API
    @PutMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long productid, @RequestBody ProductDto updatedProduct) {
        ProductDto productDto = productService.updateProduct(productid, updatedProduct);
        return ResponseEntity.ok(productDto);
    }

    //Build Delete Product REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productid) {
        productService.deleteProduct(productid);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
