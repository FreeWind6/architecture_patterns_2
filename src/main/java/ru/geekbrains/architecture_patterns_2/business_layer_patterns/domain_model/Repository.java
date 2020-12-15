package ru.geekbrains.architecture_patterns_2.business_layer_patterns.domain_model;

/**
 * repo for domain objects
 */
@org.springframework.stereotype.Repository
public class Repository {

    /**
     * логика сохранения домена в бд
     * @param order сущность {@link Order}
     * @return ид заказа
     */
    public Integer saveOrder(Order order){
        return 1;
    }

    /**
     * достает сущность {@link Branch} из бд
     * @param id ид пункта выдачи
     * @return сущность {@link Branch}
     */
    public Branch findBranch(Integer id){
        return Branch.builder().build();
    }

    /**
     * достает сущность {@link Branch} из бд
     * @param id ид пункта выдачи
     * @return сущность {@link Branch}
     */
    public Order findOrder(Integer id){
        return null;
    }
}
