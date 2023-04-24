package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

import java.util.List;

@Repository
public class GameLogDao {
    private final JdbcTemplate jdbcTemplate;

    public GameLogDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(final long gameNumber, final String playerName, final int resultPosition) {
        String sql = "insert into game_log (game_number, player_name, result_position) values (?, ?, ?)";
        jdbcTemplate.update(sql, gameNumber, playerName, resultPosition);
    }

    public List<Car> load(final long gameNumber) {
        String sql = "select player_name, result_position from game_log where game_number = ?";
        return jdbcTemplate.query(sql, (resultSet, rowNum) ->
                        new Car(resultSet.getString("player_name"), resultSet.getInt("result_position"))
                , gameNumber);
    }
}
