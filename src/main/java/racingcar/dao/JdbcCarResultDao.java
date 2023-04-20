package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.CarResult;

import java.util.List;

@Repository
public class JdbcCarResultDao implements CarResultDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final RowMapper<CarResult> entityRowMapper = (resultSet, rowNum) -> new CarResult(
            resultSet.getInt("id"),
            resultSet.getInt("game_result_id"),
            resultSet.getString("name"),
            resultSet.getInt("position")
    );

    @Autowired
    public JdbcCarResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
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

    public List<CarResult> findAllByPlayResultId(int gameResultId) {
        String sql = "SELECT * FROM car_result WHERE game_result_id = ?";
        return jdbcTemplate.query(sql, entityRowMapper, gameResultId);
    }
}
