package ru.geekbrains.architecture_patterns_2.business_layer_patterns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order")
public class Controller {

    private OrderService orderService;

    @Autowired
    public Controller(@Qualifier("transaction_script") OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/create", consumes = "application/json")
    public Integer createOrderWithTransactionScript(OrderInfoDto orderInfoDto){
        return orderService.createOrder(orderInfoDto);
    }

    @GetMapping(value = "/{id}", consumes = "application/json")
    public OrderSummary getOrderInfoWithDomain(@PathVariable Integer id){
        return orderService.getOrderInfo(id);
    }
}
