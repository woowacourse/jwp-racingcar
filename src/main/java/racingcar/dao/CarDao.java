package racingcar.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

@Repository
public class CarDao {

    private static final int IS_WINNER = 1;
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertActor;

    public CarDao(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("car")
                .usingGeneratedKeyColumns("id");
    }

    private final RowMapper<Car> carRowMapper = (resultSet, rowNum) -> new Car(
            resultSet.getString("name"),
            resultSet.getInt("position")
    );

    public int insertCar(final Car car, final int gameId) {
        Map<String, Object> parameters = new HashMap<>(4);
        parameters.put("name", car.getName());
        parameters.put("position", car.getPosition());
        parameters.put("is_winner", 0);
        parameters.put("game_id", gameId);

        return insertActor.executeAndReturnKey(parameters).intValue();
    }

    public void updatePosition(final Car car, final int gameId) {
        String sql = "UPDATE car SET position = ? WHERE game_id = ? AND name = ?";
        jdbcTemplate.update(sql, car.getPosition(), gameId, car.getName());
    }

    public void updateWinner(final String name, final int gameId) {
        String sql = "UPDATE car SET is_winner = 1 WHERE game_id = ? AND name = ?";
        jdbcTemplate.update(sql, gameId, name);
    }

    public List<Car> findWinners(int gameId) {
        String sql = "SELECT name, position FROM car WHERE game_id = ? AND is_winner = ?";
        return jdbcTemplate.query(sql, carRowMapper, gameId, IS_WINNER);
    }

    public List<Car> findCars(int gameId) {
        String sql = "SELECT name, position FROM car WHERE game_id = ?";
        return jdbcTemplate.query(sql, carRowMapper, gameId);
    }
}
