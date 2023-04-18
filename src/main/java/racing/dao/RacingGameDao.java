package racing.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racing.controller.dto.request.CarRequest;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Objects;

@Repository
public class RacingGameDao implements GameDao {

    private final JdbcTemplate jdbcTemplate;

    public RacingGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long saveGame(int count) {
        String saveGameQuery = "INSERT INTO games(count, create_time) VALUES (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(saveGameQuery, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, count);
            preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void saveCar(CarRequest car) {
        String saveCarQuery = "INSERT INTO cars(car_name, step, winner, game_id) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(saveCarQuery,
                car.getCarName(),
                car.getStep(),
                car.isWinner(),
                car.getGameId()
        );
    }

}
