package com.example.crudapp.repository;

import com.example.crudapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {

    // Spring reads the method name and writes the SQL for you!
    // "find products WHERE name LIKE %keyword% (case-insensitive)"
    List<Product> findByNameContainingIgnoreCase(String name);
}