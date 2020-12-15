package ru.geekbrains.architecture_patterns_2.business_layer_patterns;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderInfoDto {
    private LocalDate createdDate;
    private String clientName;
    private String clientPassportId;
    private String cargoName;
    private Boolean isFragile;
    private Integer fromBranchId;
    private Integer destinationBranchId;
}
