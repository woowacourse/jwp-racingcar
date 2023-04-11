package racingcar.dao;

import java.sql.PreparedStatement;
import java.util.List;

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
        final String sql = "INSERT INTO cars(game_id, name, position, is_winner) VALUES (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, cars, cars.size(), (ps, argument) -> {
            ps.setInt(1, gameId);
            ps.setString(2, argument.getName());
            ps.setInt(3, argument.getPosition());
            ps.setBoolean(4, winners.contains(argument));
        });
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
