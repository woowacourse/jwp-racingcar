package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

@Repository
public class RecordDao {

    private final JdbcTemplate jdbcTemplate;

    public RecordDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(final long gameId, final boolean isWinner, final Car car) {
        String sql = "insert into record(game_id, position, is_winner, player_name) values (?, ?, ?, ?)";

        jdbcTemplate.update(sql, gameId, car.getDistance(), isWinner, car.getName());
    }
}
