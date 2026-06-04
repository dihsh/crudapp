package com.example.crudapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity                 // tells JPA: this class = database table
@Table(name = "products") // table name in MySQL
@Data                   // Lombok: auto creates getters, setters, toString
@NoArgsConstructor      // Lombok: empty constructor
@AllArgsConstructor     // Lombok: constructor with all fields
@Builder               // Lombok: builder pattern (optional)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;           // auto-increment primary key

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false,
            message = "Price must be greater than 0")
    private Double price;

    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    @Column(updatable = false)  // set only once on creation
    private LocalDateTime createdAt;

    @PrePersist                  // runs before INSERT into DB
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}