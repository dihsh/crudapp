package com.example.crudapp.controller;

import com.example.crudapp.model.Product;
import com.example.crudapp.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController             // handles HTTP requests, returns JSON
@RequestMapping("/api/products") // base URL for all methods
@RequiredArgsConstructor    // Lombok: injects 'service'
public class ProductController {

    private final ProductService service;

    // GET http://localhost:8080/api/products
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // GET http://localhost:8080/api/products/1
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // GET http://localhost:8080/api/products/search?name=laptop
    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(
            @RequestParam String name) {
        return ResponseEntity.ok(service.search(name));
    }

    // POST http://localhost:8080/api/products
    @PostMapping
    public ResponseEntity<Product> create(
            @Valid @RequestBody Product product) {
        // @Valid triggers the @NotBlank/@NotNull checks in Product.java
        // @RequestBody reads the JSON from Postman body
        Product saved = service.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT http://localhost:8080/api/products/1
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Long id,
            @Valid @RequestBody Product product) {
        return ResponseEntity.ok(service.update(id, product));
    }

    // DELETE http://localhost:8080/api/products/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }
}