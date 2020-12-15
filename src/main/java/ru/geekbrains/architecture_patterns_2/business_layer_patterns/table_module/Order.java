package ru.geekbrains.architecture_patterns_2.business_layer_patterns.table_module;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class Order {
    private Integer id;
    private LocalDate createdDate;
    private Integer fromBranchId;
    private Integer toBranchId;
    private Integer cargoId;

    public double calculatePrice(Road road, Cargo cargo) {
        double price = road.calculateDistance() * 1.5;
        if (cargo.getIsFragile()) {
            return price * 2;
        }
        return price;
    }
}
