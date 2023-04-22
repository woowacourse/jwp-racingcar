package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import racingcar.domain.Car;

import java.util.List;

@Component
public class WinnersDao {

    private final JdbcTemplate jdbcTemplate;

    public WinnersDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(final long gameNumber, final String winner) {
        String sql = "insert into winners(game_number, winner) values(?,?)";
        jdbcTemplate.update(sql, gameNumber, winner);
    }

    public List<Car> load(final long gameNumber) {
        String sql = "select winner from winners where game_number = ?";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new Car(resultSet.getString("winner"))
                , gameNumber);
    }
}
