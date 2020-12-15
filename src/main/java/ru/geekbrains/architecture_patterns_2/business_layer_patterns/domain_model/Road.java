package ru.geekbrains.architecture_patterns_2.business_layer_patterns.domain_model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Road {
    private Branch from;
    private Branch to;

    public double calculateDistance(){
        return Math.hypot((to.getLatitude() - from.getLatitude()), (to.getLongitude() - from.getLongitude()));
    }
}
