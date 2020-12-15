package ru.geekbrains.architecture_patterns_2.business_layer_patterns.transaction_script;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.geekbrains.architecture_patterns_2.business_layer_patterns.OrderInfoDto;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 * repo for transaction script
 */
@org.springframework.stereotype.Repository
public class Repository {

    private final String CREATE_CLIENT_QUERY = "insert into client (name, passportId) values(? , ?)";
    private final String CREATE_ORDER_QUERY = "insert into order (createdDate, clientId, cargoId, branchId, status) " +
            "values(? , ? , ? , ? , ?)";

    private JdbcTemplate jdbcTemplate;

    public Repository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * создает клиента на основании {@link OrderInfoDto}
     *
     * @param orderInfoDto информация о новом заказе с фронта
     * @return ид клиента
     */
    public Integer createClient(OrderInfoDto orderInfoDto) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            CREATE_CLIENT_QUERY,
                            Statement.RETURN_GENERATED_KEYS
                    );
                    preparedStatement.setString(1, orderInfoDto.getClientName());
                    preparedStatement.setString(2, orderInfoDto.getClientPassportId());
                    return preparedStatement;
                },
                holder
        );
        return holder.getKey().intValue();
    }

    public Integer createCargo(OrderInfoDto orderInfoDto) {
        //По аналогии с верхним методом
        return 1;
    }

    public Integer createOrder(OrderInfoDto orderInfoDto, Integer clientId, Integer cargoId) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            CREATE_ORDER_QUERY,
                            Statement.RETURN_GENERATED_KEYS
                    );
                    preparedStatement.setTimestamp(1, Timestamp.valueOf(orderInfoDto.getCreatedDate().atStartOfDay()));
                    preparedStatement.setInt(2, clientId);
                    preparedStatement.setInt(3, orderInfoDto.getDestinationBranchId());
                    preparedStatement.setInt(4, cargoId);
                    preparedStatement.setString(5, "created");
                    return preparedStatement;
                },
                holder
        );
        return holder.getKey().intValue();
    }

    /**
     * возвращает из бд запрос с информацией о заказе
     *
     * @param id идентификатор заказа
     * @return информация о заказе
     */
    public OrderInfoDbDto getOrderInfo(Integer id) {
        return jdbcTemplate.query(
                "select o.created_date, " +
                        "from_branch.name, from_branch.latitude, from_branch.longitude" +
                        "to_branch.name, to_branch.latitude, to_branch.longitude" +
                        "car.isFragile" +
                        " from order o" +
                        "join cargo car on o.cargoId = car.id" +
                        "join branch from_branch on from_branch.id = o.branch_from_id" +
                        "join branch to_branch on to_branch.id = o.branch_to_id" +
                        "where o.id = ?",
                (r, i) -> OrderInfoDbDto.builder()
                        .createdDate(r.getTimestamp(1).toLocalDateTime().toLocalDate())
                        .fromBranch(r.getString(2))
                        .fromLatitude(r.getDouble(3))
                        .fromLongitude(r.getDouble(4))
                        .toBranch(r.getString(5))
                        .toLatitude(r.getDouble(6))
                        .toLongitude(r.getDouble(7))
                        .isFragile(r.getBoolean(8))
                        .build(),
                id
        ).stream().findAny().orElse(null);
    }
}