package racingcar.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.model.Car;

@Repository
public class RacingCarDao {
    private final JdbcTemplate jdbcTemplate;

    public RacingCarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(final Car car, final int gameId, final boolean isWinner) {
        final String sql = "INSERT INTO racing_car(name,position,racing_game_id,is_winner) VALUES (?,?,?,?)";
        this.jdbcTemplate.update(sql, car.getName(), car.getPosition(), gameId, isWinner);
    }
}
