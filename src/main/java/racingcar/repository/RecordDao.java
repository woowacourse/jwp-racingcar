package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.model.Vehicle;

@Repository
public class RecordDao {
    private final JdbcTemplate jdbcTemplate;

    public RecordDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(int gameId, boolean isWinner, Vehicle vehicle) {
        String sql = "insert into record(game_id, position, is_winner, player_name) values (?, ?, ?, ?)";

        jdbcTemplate.update(sql, gameId, vehicle.getDistance(), isWinner, vehicle.getName());
    }
}
