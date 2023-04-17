package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import racingcar.dao.entity.CarEntity;

public class SelectCarDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<CarEntity> actorRowMapper = (resultSet, rowNum) -> new CarEntity(
            resultSet.getInt("car_id"),
            resultSet.getString("name"),
            resultSet.getInt("position")
    );

    public SelectCarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CarEntity> findAllByGameId(final int gameId) {
        final String sql = "SELECT car_id, name, position FROM CAR WHERE game_id = ?";

        return jdbcTemplate.query(sql, actorRowMapper, gameId);
    }
}
