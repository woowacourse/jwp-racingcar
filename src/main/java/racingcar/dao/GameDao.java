package racingcar.dao;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import racingcar.dao.dto.TrialCountDto;
import racingcar.domain.Car;
import racingcar.domain.Game;

public class GameDao {

    private final JdbcTemplate jdbcTemplate;

    public GameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertResult(final Game game) {
        final String sql = "INSERT INTO games(trial_count) VALUES (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[] {"id"});
            preparedStatement.setInt(1, game.getTrialCount());
            return preparedStatement;
        }, keyHolder);
        int key = keyHolder.getKey().intValue();

        insertCars(key, game.getCars(), game.findWinners());

        return key;
    }

    private void insertCars(final int gameId, final List<Car> cars, final List<Car> winners) {
        final String carsSql = "INSERT INTO cars(game_id, name, position) VALUES (?, ?, ?)";
        final Map<Car, Integer> carIds = new HashMap<>();

        for (Car car : cars) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(carsSql, new String[] {"id"});
                preparedStatement.setInt(1, gameId);
                preparedStatement.setString(2, car.getName());
                preparedStatement.setInt(3, car.getPosition());
                return preparedStatement;
            }, keyHolder);
            carIds.put(car, keyHolder.getKey().intValue());
        }

        final String winnersSql = "INSERT INTO winners(game_id, car_id) VALUES (?, ?)";
        for (Car car : winners) {
            jdbcTemplate.update(winnersSql, gameId, carIds.get(car));
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
