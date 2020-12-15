package ru.geekbrains.architecture_patterns_2.business_layer_patterns;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OrderSummary {
    private LocalDate createdDate;
    private String from;
    private String to;
    private Double price;
}
