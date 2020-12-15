package ru.geekbrains.architecture_patterns_2.business_layer_patterns.transaction_script;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OrderInfoDbDto {
    private LocalDate createdDate;
    private String fromBranch;
    private Double fromLatitude;
    private Double fromLongitude;
    private String toBranch;
    private Double toLatitude;
    private Double toLongitude;
    private Boolean isFragile;
}
