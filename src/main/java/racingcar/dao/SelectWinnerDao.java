package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import racingcar.dao.entity.WinnerEntity;

public class SelectWinnerDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<WinnerEntity> actionRowMapper = (resultSet, rowNum) -> new WinnerEntity(
            resultSet.getInt("game_id"),
            resultSet.getInt("car_id"),
            resultSet.getInt("winner_id")
    );

    public SelectWinnerDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<WinnerEntity> findAllByGameId(final int gameId) {
        final String sql = "SELECT winner_id, game_id, car_id FROM WINNER WHERE game_id = ?";

        return jdbcTemplate.query(sql, actionRowMapper, gameId);
    }
}
