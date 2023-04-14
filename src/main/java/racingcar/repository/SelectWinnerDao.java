package racingcar.repository;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class SelectWinnerDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Integer> actorRowMapper = (resultSet, rowNum) -> resultSet.getInt("car_id");

    public SelectWinnerDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Integer> findByGameId(final int gameId) {
        final String sql = "SELECT car_id FROM WINNER WHERE game_id = ?";

        return jdbcTemplate.query(sql, actorRowMapper, gameId);
    }
}
