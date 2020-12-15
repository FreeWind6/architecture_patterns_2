package ru.geekbrains.architecture_patterns_2.business_layer_patterns.table_module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public Integer createOrder(OrderInfoDto orderInfoDto) {
        Cargo cargo = factory.createCargo(orderInfoDto);
        Integer cargoId = repository.saveCargo(cargo);
        Order order = factory.createOrder(orderInfoDto, cargoId);
        return repository.saveOrder(order);
    }

    @Override
    public OrderSummary getOrderInfo(Integer id) {
        Order order = repository.findOrder(id);
        Branch from  = repository.findBranch(order.getFromBranchId());
        Branch to  = repository.findBranch(order.getToBranchId());
        Cargo cargo = repository.findCargo(order.getCargoId());
        Road road = Road.builder()
                .from(from)
                .to(to)
                .build();
        return OrderSummary.builder()
                .createdDate(order.getCreatedDate())
                .from(from.getName())
                .to(to.getName())
                .price(order.calculatePrice(road, cargo))
                .build();
    }
}
