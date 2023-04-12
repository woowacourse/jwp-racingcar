package racingcar.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.dao.dto.TrialCountDto;
import racingcar.domain.Car;
import racingcar.domain.Game;

@Repository
public class GameDao {

    private final JdbcTemplate jdbcTemplate;

    public GameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertResult(final Game game) {
        int gameId = insertGame(game);
        Map<Car, Integer> carIds = insertCars(gameId, game.getCars());
        insertWinners(gameId, carIds, game.findWinners());
    }

    private int insertGame(Game game) {
        final SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("games")
                .usingColumns("trial_count")
                .usingGeneratedKeyColumns("id");

        final SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("trial_count", game.getTrialCount());

        return simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue();
    }

    private Map<Car, Integer> insertCars(final int gameId, final List<Car> cars) {
        final SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("cars")
                .usingGeneratedKeyColumns("id");

        final Map<Car, Integer> carIds = new HashMap<>();
        for (Car car : cars) {
            final SqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("game_id", gameId)
                    .addValue("name", car.getName())
                    .addValue("position", car.getPosition());

            int carId = simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue();
            carIds.put(car, carId);
        }
        return carIds;
    }

    private void insertWinners(final int gameId, Map<Car, Integer> carIds, final List<Car> winners) {
        final SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("winners");
        for (Car car : winners) {
            final SqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("game_id", gameId)
                    .addValue("car_id", carIds.get(car));

            simpleJdbcInsert.execute(parameterSource);
        }
    }

    private final RowMapper<TrialCountDto> rowMapper = (resultSet, rowNum) -> new TrialCountDto(
            resultSet.getInt("id"),
            resultSet.getInt("trial_count")
    );

    public List<TrialCountDto> selectAll() {
        final String sql = "SELECT id, trial_count FROM games";

        return jdbcTemplate.query(sql, rowMapper);
    }
}
