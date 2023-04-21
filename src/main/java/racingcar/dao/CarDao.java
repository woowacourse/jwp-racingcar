package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.CarEntity;

import java.util.List;

@Repository
public class CarDao {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public CarDao(final JdbcTemplate jdbcTemplate) {
        this.simpleJdbcInsert =
                new SimpleJdbcInsert(jdbcTemplate)
                        .withTableName("CAR")
                        .usingGeneratedKeyColumns("id");
    }

    public void save(final List<CarEntity> carEntities) {
        simpleJdbcInsert.executeBatch(SqlParameterSourceUtils.createBatch(carEntities));
    }
}
