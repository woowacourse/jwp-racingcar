package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import racingcar.dao.entity.CarEntity;

public class InsertCarDao {

    private final SimpleJdbcInsert insertActor;

    public InsertCarDao(final JdbcTemplate jdbcTemplate) {
        insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("car")
                .usingGeneratedKeyColumns("car_id");
    }

    public void insertAll(final List<CarEntity> carEntities) {
        final BeanPropertySqlParameterSource[] batch = carEntities.stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(BeanPropertySqlParameterSource[]::new);
        insertActor.executeBatch(batch);
    }
}
