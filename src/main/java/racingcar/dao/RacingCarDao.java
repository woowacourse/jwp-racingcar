package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.domain.RacingCarDto;

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

    public List<RacingCarDto> findBy(long resultId) {
        String sql = "select name, position from racing_cars where result_id = ?";
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    String name = rs.getString("name");
                    int position = rs.getInt("position");
                    RacingCarDto racingCarDto = new RacingCarDto(name, position);
                    return racingCarDto;
                }, resultId);
    }
}
