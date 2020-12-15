package ru.geekbrains.architecture_patterns_2.business_layer_patterns;

public interface OrderService {
    Integer createOrder(OrderInfoDto orderInfoDto);

    OrderSummary getOrderInfo(Integer id);
}
