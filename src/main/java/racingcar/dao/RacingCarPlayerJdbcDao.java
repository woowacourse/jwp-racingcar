package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;

@Component
public class RacingCarPlayerJdbcDao implements RacingCarPlayerDao {

    private final JdbcTemplate jdbcTemplate;

    public RacingCarPlayerJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertGameLog(final RacingCars racingCars, final int gameId) {
        final String sql = "INSERT INTO LOG (game_id, name, move) values (?,?,?)";

        for (RacingCar racingCar : racingCars.getRacingCars()) {
            jdbcTemplate.update(sql, gameId, racingCar.getName(), racingCar.getPosition());
        }
    }
}
