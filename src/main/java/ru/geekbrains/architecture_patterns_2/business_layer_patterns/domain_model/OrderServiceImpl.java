package ru.geekbrains.architecture_patterns_2.business_layer_patterns.domain_model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.architecture_patterns_2.business_layer_patterns.OrderInfoDto;
import ru.geekbrains.architecture_patterns_2.business_layer_patterns.OrderService;
import ru.geekbrains.architecture_patterns_2.business_layer_patterns.OrderSummary;

@Service("domain")
public class OrderServiceImpl implements OrderService {

    private Factory factory;
    private Repository repository;

    @Autowired
    public OrderServiceImpl(Factory factory, Repository repository) {
        this.factory = factory;
        this.repository = repository;
    }

    @Override
    public Integer createOrder(OrderInfoDto orderInfoDto) {
        Branch destinationBranch = repository.findBranch(orderInfoDto.getDestinationBranchId());
        Branch fromBranch = repository.findBranch(orderInfoDto.getFromBranchId());
        Order order = factory.createOrder(orderInfoDto, destinationBranch, fromBranch);
        return repository.saveOrder(order);
    }

    @Override
    public OrderSummary getOrderInfo(Integer id) {
        Order order = repository.findOrder(id);
        return OrderSummary.builder()
                .createdDate(order.getCreatedDate())
                .from(order.getRoad().getFrom().getName())
                .to(order.getRoad().getTo().getName())
                .price(order.calculatePrice())
                .build();
    }
}
