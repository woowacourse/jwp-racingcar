package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import racingcar.entity.CarEntity;

@Component
public class CarJdbcDao implements CarDao {
    private final SimpleJdbcInsert simpleJdbcInsert;

    public CarJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Car")
                .usingGeneratedKeyColumns("id");
    }

    public void saveAll(final List<CarEntity> cars) {
        final BeanPropertySqlParameterSource[] parameterSources = cars.stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(BeanPropertySqlParameterSource[]::new);
        simpleJdbcInsert.executeBatch(parameterSources);
    }
}
