package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.domain.Name;

@Repository
public class GameLogDao {
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Car> carRowMapper = (resultSet, rowNum) -> {
        Car car = new Car(
                new Name(resultSet.getString("player_name")),
                resultSet.getInt("result_position")
        );
        return car;
    };

    public GameLogDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(int gameNumber, String playerName, int resultPosition) {
        String sql = "insert into game_log (game_number, player_name, result_position) values (?, ?, ?)";
        jdbcTemplate.update(sql, gameNumber, playerName, resultPosition);
    }

    public List<Car> find(int gameNumber) {
        String sql = "select gl.player_name, gl.result_position from game_log gl where gl.game_number = ?";
        return jdbcTemplate.query(sql, carRowMapper, gameNumber);
    }
}
