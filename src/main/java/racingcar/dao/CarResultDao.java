package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.CarResult;

import javax.sql.DataSource;

@Repository
public class CarResultDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final RowMapper<CarResult> entityRowMapper = (resultSet, rowNum) -> {
        CarResult carResult = CarResult.of(
                resultSet.getLong("id"),
                resultSet.getLong("play_result_id"),
                resultSet.getString("name"),
                resultSet.getInt("position")
        );
        return carResult;
    };

    public CarResultDao(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("car_result")
                .usingGeneratedKeyColumns("id");
    }

    public long save(CarResult carResult) {
        SqlParameterSource source = new BeanPropertySqlParameterSource(carResult);
        return simpleJdbcInsert.executeAndReturnKey(source).longValue();
    }

    public CarResult findById(long id) {
        String sql = "SELECT * FROM car_result WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, entityRowMapper, id);
    }
}
