package racingcar.dao.web;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.dao.CarDao;
import racingcar.domain.Car;

@Repository
public class WebCarDao implements CarDao {

    private final JdbcTemplate jdbcTemplate;

    public WebCarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Car> carRowMapper = (resultSet, rowNum) -> new Car(
            resultSet.getString("name"),
            resultSet.getInt("position")
    );

    public void insertCars(final List<Car> cars, final int gameId) {
        String sql = "INSERT INTO car (name, position, is_winner, game_id) VALUES (?, ?, 0, ?)";
        jdbcTemplate.batchUpdate(sql, cars, cars.size(),
                (ps, car) -> {
                    ps.setString(1, car.name());
                    ps.setInt(2, car.position());
                    ps.setInt(3, gameId);
                });
    }

    public void updateWinners(final List<Car> cars, final int gameId) {
        String sql = "UPDATE car SET is_winner = 1 WHERE game_id = ? AND name = ?";
        jdbcTemplate.batchUpdate(sql, cars, cars.size(),
                (ps, car) -> {
                    ps.setInt(1, gameId);
                    ps.setString(2, car.name());
                });
    }

    public void updatePositions(final List<Car> cars, final int gameId) {
        String sql = "UPDATE car SET position = ? WHERE game_id = ? AND name = ?";
        jdbcTemplate.batchUpdate(sql, cars, cars.size(),
                (ps, car) -> {
                    ps.setInt(1, car.position());
                    ps.setInt(2, gameId);
                    ps.setString(3, car.name());
                });
    }

    public List<Car> findWinners(int gameId) {
        String sql = "SELECT name, position FROM car WHERE game_id = ? AND is_winner = 1";
        return jdbcTemplate.query(sql, carRowMapper, gameId);
    }

    public List<Car> findCars(int gameId) {
        String sql = "SELECT name, position FROM car WHERE game_id = ?";
        return jdbcTemplate.query(sql, carRowMapper, gameId);
    }
}
