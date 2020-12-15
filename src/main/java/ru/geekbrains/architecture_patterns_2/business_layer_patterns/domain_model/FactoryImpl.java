package ru.geekbrains.architecture_patterns_2.business_layer_patterns.domain_model;

import ru.geekbrains.architecture_patterns_2.business_layer_patterns.OrderInfoDto;

import java.time.LocalDate;

public class FactoryImpl implements Factory {
    @Override
    public Order createOrder(OrderInfoDto orderInfoDto, Branch branch, Branch fromBranch) {
        Cargo cargo = new Cargo(null, orderInfoDto.getCargoName(), orderInfoDto.getIsFragile());
        return new Order(null, LocalDate.now(), new Road(fromBranch, branch), cargo);
    }
}
