package racing.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class RacingGameDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public RacingGameDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
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

    public void saveCar(List<CarEntity> carEntities) {
        String saveCarQuery = "INSERT INTO cars(car_name, step, winner, game_id) VALUES (?, ?, ?, ?)";
        List<Object[]> insertValues = new ArrayList<>();

        for (CarEntity carEntity : carEntities) {
            insertValues.add(new Object[]{
                    carEntity.getCarName(),
                    carEntity.getStep(),
                    carEntity.isWinner(),
                    carEntity.getGameId(),
            });
        }
        jdbcTemplate.batchUpdate(saveCarQuery, insertValues);
    }

    public int findGameTrialById(Long gameId) {
        String findGameById = "SELECT count FROM games AS g WHERE g.game_id = ?";

        return jdbcTemplate.queryForObject(findGameById, Integer.class, gameId);
    }

    public List<Long> findAllGameIdOrderByRecent() {
        String findAllGamesOrderByRecentQuery = "SELECT game_id FROM games AS g ORDER BY g.create_time DESC";

        return jdbcTemplate.query(findAllGamesOrderByRecentQuery, (rs, rowNum) -> rs.getLong("game_id"));
    }

    public List<CarEntity> findCarsInGame(List<Long> gameIds) {
        String findCarsInGame = "SELECT * FROM cars WHERE game_id IN (:gameIds)";
        MapSqlParameterSource parameters = new MapSqlParameterSource("gameIds", gameIds);

        return namedParameterJdbcTemplate.query(findCarsInGame, parameters, (rs, rowNum) -> {
            String carName = rs.getString("car_name");
            int step = rs.getInt("step");
            boolean winner = rs.getBoolean("winner");
            Long gameId = rs.getLong("game_id");

            return CarEntity.of(gameId, carName, step, winner);
        });
    }
}
