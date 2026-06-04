package com.example.crudapp.service;

import com.example.crudapp.exception.ResourceNotFoundException;
import com.example.crudapp.model.Product;
import com.example.crudapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service                  // registers this as a Spring-managed bean
@RequiredArgsConstructor  // Lombok: injects 'repo' automatically
public class ProductService {

    private final ProductRepository repo;
    // ↑ Spring injects this automatically because of @RequiredArgsConstructor

    // ── READ ALL ──────────────────────────────────────
    public List<Product> getAll() {
        return repo.findAll();
    }

    // ── READ ONE ──────────────────────────────────────
    public Product getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with id: " + id
                ));
    }

    // ── CREATE ────────────────────────────────────────
    public Product create(Product product) {
        return repo.save(product);
        // repo.save() does INSERT when id is null
    }

    // ── UPDATE ────────────────────────────────────────
    public Product update(Long id, Product updated) {
        Product existing = getById(id); // throws 404 if not found
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
        existing.setQuantity(updated.getQuantity());
        return repo.save(existing);
        // repo.save() does UPDATE when id already exists
    }

    // ── DELETE ────────────────────────────────────────
    public void delete(Long id) {
        getById(id);            // throws 404 if product doesn't exist
        repo.deleteById(id);
    }

    // ── SEARCH ────────────────────────────────────────
    public List<Product> search(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }
}