package com.example.incomesystem.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transactiondto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    @Size(min = 3, max = 100, message = "Product name must be between 3 and 100 characters")
    private String productname;

    @Min(value = 0, message = "Credited amount must be greater than or equal to 0")
    private Double credited;

    @Min(value = 0, message = "Debited amount must be greater than or equal to 0")
    private Double debited;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;
}