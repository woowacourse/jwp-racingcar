package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.model.Car;

import java.util.List;

@Repository
public class InMemoryCarDao implements CarDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InMemoryCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAll(int gameId, List<Car> cars) {
        String sql = "insert into CAR_RESULT (play_result_id, car_name, car_position) values (?, ?, ?)";

        for (Car car : cars) {
            jdbcTemplate.update(sql, gameId, car.getName(), car.getLocation());
        }
    }
}
