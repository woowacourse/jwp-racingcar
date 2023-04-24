package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.entity.RacingCar;

import java.util.List;

@Repository
public class RacingCarDao {

    private JdbcTemplate jdbcTemplate;

    public RacingCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Car car, long resultId) {
        String sql = "insert into racing_cars (name, position, result_id) values (?, ?, ?)";
        jdbcTemplate.update(sql, car.getName(), car.getLocation(), resultId);
    }

    private final RowMapper<RacingCar> actorRowMapper = (resultSet, rowNum) -> {
        RacingCar racingCar = new RacingCar(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("position"),
                resultSet.getLong("result_id")
        );
        return racingCar;
    };

    public List<RacingCar> findBy(long resultId) {
        String sql = "select * from racing_cars where result_id = ?";
        return jdbcTemplate.query(sql, actorRowMapper, resultId);
    }
}
