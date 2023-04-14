package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.Vehicle;

@Repository
public class RecordDao {

    private final JdbcTemplate jdbcTemplate;

    public RecordDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(final long gameId, final boolean isWinner, final Vehicle vehicle) {
        String sql = "insert into record(game_id, position, is_winner, player_name) values (?, ?, ?, ?)";

        jdbcTemplate.update(sql, gameId, vehicle.getDistance(), isWinner, vehicle.getName());
    }
}
