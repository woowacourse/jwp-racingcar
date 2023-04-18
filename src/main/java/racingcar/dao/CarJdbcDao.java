package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import racingcar.entity.CarEntity;

@Component
public class CarJdbcDao implements CarDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private final RowMapper<CarEntity> rowMapper = (resultSet, rowNum) -> new CarEntity(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getInt("position"),
            resultSet.getBoolean("winner"),
            resultSet.getInt("game_id")
    );

    public CarJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Car")
                .usingGeneratedKeyColumns("id");
    }

    public void saveAll(final List<CarEntity> cars) {
        final BeanPropertySqlParameterSource[] parameterSources = cars.stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(BeanPropertySqlParameterSource[]::new);
        jdbcInsert.executeBatch(parameterSources);
    }

    @Override
    public List<CarEntity> findAll() {
        final String sql = "SELECT * FROM CAR";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
