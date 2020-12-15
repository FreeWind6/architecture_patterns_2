package ru.geekbrains.architecture_patterns_2.business_layer_patterns.domain_model;


import ru.geekbrains.architecture_patterns_2.business_layer_patterns.OrderInfoDto;


public interface Factory {

    Order createOrder(OrderInfoDto orderInfoDto, Branch branch, Branch fromBranch);
}
