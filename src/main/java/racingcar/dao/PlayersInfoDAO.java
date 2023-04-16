package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

import java.util.List;

@Repository
public class PlayersInfoDAO {

    private final JdbcTemplate jdbcTemplate;

    public PlayersInfoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(int parentId, List<Car> cars) {
        String sql = "insert into players_info (name, position, play_result_id) values (?, ?, ?)";
        for (Car car : cars) {
            jdbcTemplate.update(sql, car.getName(), car.getPosition(), parentId);
        }
    }

}
