package racing.persist.car;

import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class H2CarDao implements CarDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public H2CarDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void saveAllCar(List<CarEntity> carEntities) {
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

    public List<CarEntity> findCarsInGame(Long gameId) {
        String findCarsInGame = "SELECT * FROM cars WHERE game_id IN (:gameIds)";
        MapSqlParameterSource parameters = new MapSqlParameterSource("gameIds", gameId);

        return namedParameterJdbcTemplate.query(findCarsInGame, parameters, (rs, rowNum) -> {
            String carName = rs.getString("car_name");
            int step = rs.getInt("step");
            boolean winner = rs.getBoolean("winner");

            return CarEntity.of(gameId, carName, step, winner);
        });
    }
}
