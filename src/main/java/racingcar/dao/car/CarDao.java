package racingcar.dao.car;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.CarEntity;

import java.util.List;

@Repository
public class CarDao {

    private final JdbcTemplate jdbcTemplate;

    public CarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(final List<CarEntity> carEntities) {
        final SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        jdbcInsert.withTableName("CAR")
                  .usingGeneratedKeyColumns("id");

        final SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(carEntities);

        jdbcInsert.executeBatch(batch);
    }
}
