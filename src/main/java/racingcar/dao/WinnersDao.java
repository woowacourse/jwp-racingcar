package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.domain.Name;

@Repository
public class WinnersDao {

    private JdbcTemplate jdbcTemplate;

    public WinnersDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Car> carRowMapper = (resultSet, rowNum) -> {
        Car car = new Car(
                new Name(resultSet.getString("player_name")),
                resultSet.getInt("result_position")
        );
        return car;
    };

    public void insert(int gameNumber, String winner) {
        String sql = "insert into winners(game_number, winner) values(?,?)";
        jdbcTemplate.update(sql, gameNumber, winner);
    }

    public List<Car> find(int gameNumber) {
        String sql = "select gl.game_number, gl.player_name, gl.result_position "
                + "from game_log gl, winners w "
                + "where w.game_number = gl.game_number and w.winner = gl.player_name and w.game_number = ?";
        return jdbcTemplate.query(sql, carRowMapper, gameNumber);
    }
}
