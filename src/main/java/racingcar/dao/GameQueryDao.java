package racingcar.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

@Repository
public class GameQueryDao {

    private final JdbcTemplate jdbcTemplate;

    public GameQueryDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Integer> getGameIds() {
        final String sql = "select id from GAME";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> resultSet.getInt("id")
        );
    }

    public List<Car> getCars(final int id) {
        final String sql = "select name, position from PLAYER where game_id = ?";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new Car(
                        resultSet.getString("name"),
                        resultSet.getInt("position")
                ),
                id
        );
    }

    public List<String> getWinners(final int id) {
        final String sql = "select name from PLAYER where game_id = ? and isWinner = 1";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> resultSet.getString("name"),
                id
        );
    }
}
