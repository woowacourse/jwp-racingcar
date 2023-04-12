package racing;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Objects;

@Repository
public class RacingGameDao {

    private final JdbcTemplate jdbcTemplate;

    public RacingGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void initTables() {
        jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS cars (" +
                        "car_id bigint NOT NULL AUTO_INCREMENT, " +
                        "car_name varchar(8) NOT NULL, " +
                        "step int(3) NOT NULL, " +
                        "winner boolean default false, " +
                        "game_id bigint NOT NUll, " +
                        "PRIMARY KEY (car_id)" +
                        ");"
        );

        jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS games (" +
                        "game_id bigint NOT NULL AUTO_INCREMENT, " +
                        "count int(3) NOT NULL, " +
                        "create_time timestamp NOT NULL, " +
                        "PRIMARY KEY (game_id)" +
                        ");"
        );
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

    public void saveCar(CarEntity car) {
        String saveCarQuery = "INSERT INTO cars(car_name, step, winner, game_id) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(saveCarQuery,
                car.getCarName(),
                car.getStep(),
                car.isWinner(),
                car.getGameId()
        );
    }

}
