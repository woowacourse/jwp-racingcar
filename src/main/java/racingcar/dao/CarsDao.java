package racingcar.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import racingcar.dao.dto.CarDto;

@Component
public class CarsDao {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;

    public CarsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("cars")
                .usingGeneratedKeyColumns("id");
    }

    public int insert(int gameId, String name, int position) {
        final SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("game_id", gameId)
                .addValue("name", name)
                .addValue("position", position);

        return simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue();
    }

    public List<CarDto> selectAllBy(int gameId) {
        String sql = "SELECT * from cars WHERE game_id = ?";

        return jdbcTemplate.query(
                sql,
                (resultSet, rowCount) -> new CarDto(
                        resultSet.getString("name"),
                        resultSet.getInt("position")
                ),
                gameId
        );
    }
}
