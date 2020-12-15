package ru.geekbrains.architecture_patterns_2.business_layer_patterns.transaction_script;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.architecture_patterns_2.business_layer_patterns.OrderInfoDto;
import ru.geekbrains.architecture_patterns_2.business_layer_patterns.OrderService;
import ru.geekbrains.architecture_patterns_2.business_layer_patterns.OrderSummary;

@Service("transaction_script")
public class OrderServiceImpl implements OrderService {

    private Repository repository;

    @Autowired
    public OrderServiceImpl(Repository repository) {
        this.repository = repository;
    }

    @Transactional
    public Integer createOrder(OrderInfoDto orderInfoDto){
        Integer client = repository.createClient(orderInfoDto);
        Integer cargo = repository.createCargo(orderInfoDto);
        return repository.createOrder(orderInfoDto, client, cargo);
    }

    @Override
    public OrderSummary getOrderInfo(Integer id) {
        OrderInfoDbDto orderInfo = repository.getOrderInfo(id);
        return OrderSummary.builder()
                .from(orderInfo.getFromBranch())
                .to(orderInfo.getToBranch())
                .createdDate(orderInfo.getCreatedDate())
                .price(calculatePrice(orderInfo))
                .build();
    }

    private double calculatePrice(OrderInfoDbDto orderInfoDbDto){
        double distance = Math.hypot(
                orderInfoDbDto.getToLatitude() - orderInfoDbDto.getFromLatitude(),
                orderInfoDbDto.getToLongitude() - orderInfoDbDto.getFromLongitude()
        );

        if (orderInfoDbDto.getIsFragile()){
            return distance * 2;
        }
        return distance;
    }
}
