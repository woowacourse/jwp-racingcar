package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.CarResult;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CarResultDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final RowMapper<CarResult> entityRowMapper = (resultSet, rowNum) -> CarResult.of(
            resultSet.getInt("id"),
            resultSet.getInt("play_result_id"),
            resultSet.getString("name"),
            resultSet.getInt("position")
    );

    public CarResultDao(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("car_result")
                .usingGeneratedKeyColumns("id");
    }

    public int save(CarResult carResult) {
        SqlParameterSource source = new BeanPropertySqlParameterSource(carResult);
        return simpleJdbcInsert.executeAndReturnKey(source).intValue();
    }

    public CarResult findById(int id) {
        String sql = "SELECT * FROM car_result WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, entityRowMapper, id);
    }

    public List<CarResult> findAllByPlayResultId(int playResultId) {
        String sql = "SELECT * FROM car_result WHERE play_result_id = ?";
        return jdbcTemplate.query(sql, entityRowMapper, playResultId);
    }
}
