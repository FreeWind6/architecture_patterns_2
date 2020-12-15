package ru.geekbrains.architecture_patterns_2.business_layer_patterns.table_module;


import ru.geekbrains.architecture_patterns_2.business_layer_patterns.OrderInfoDto;

public interface Factory {

    Order createOrder(OrderInfoDto orderInfoDto, Integer cargoId);

    Cargo createCargo(OrderInfoDto orderInfoDto);
}
