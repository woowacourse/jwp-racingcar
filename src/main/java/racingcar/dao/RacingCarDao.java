package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

@Repository
public class RacingCarDao {

    private final JdbcTemplate jdbcTemplate;

    public RacingCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Car car, long resultId) {
        String sql = "INSERT INTO RACING_CARS (NAME, POSITION, RESULT_ID) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, car.getName(), car.getLocation(), resultId);
    }


}
