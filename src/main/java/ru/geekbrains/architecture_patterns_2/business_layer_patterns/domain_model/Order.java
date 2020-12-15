package ru.geekbrains.architecture_patterns_2.business_layer_patterns.domain_model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class Order {
    private Integer id;
    private LocalDate createdDate;
    private Road road;
    private Cargo cargo;

    public double calculatePrice() {
        double price = road.calculateDistance();
        if (cargo.getIsFragile()) {
            return price * 2;
        }
        return price;
    }
}
