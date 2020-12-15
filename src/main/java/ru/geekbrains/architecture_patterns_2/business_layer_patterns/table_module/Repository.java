package ru.geekbrains.architecture_patterns_2.business_layer_patterns.table_module;

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
     * логика сохранения домена в бд
     * @param cargo сущность {@link Cargo}
     * @return ид заказа
     */
    public Integer saveCargo(Cargo cargo){
        return 1;
    }

    /**
     * достает табличную сущность {@link Branch} из бд
     * @param id ид пункта выдачи
     * @return сущность {@link Branch}
     */
    public Branch findBranch(Integer id){
        return Branch.builder().build();
    }

    public Branch findBranchByOrder(Order order){
        return Branch.builder().build();
    }

    /**
     * достает табличную сущность {@link Branch} из бд
     * @param id ид пункта выдачи
     * @return сущность {@link Branch}
     */
    public Order findOrder(Integer id){
        return null;
    }

    /**
     * достает табличную сущность {@link Cargo} из бд
     * @param id ид пункта выдачи
     * @return сущность {@link Cargo}
     */
    public Cargo findCargo(Integer id){
        return null;
    }
}
