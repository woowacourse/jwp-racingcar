package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PlayersInfoDAO {

    private JdbcTemplate jdbcTemplate;

    public PlayersInfoDAO(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(int parentId, List<Car> cars) {
        String sql = "insert into player_info (name, position, play_result_id) values (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql,
                cars,
                10,
                (PreparedStatement ps, Car car) -> {
                    ps.setString(1, car.getName());
                    ps.setInt(2, car.getPosition());
                    ps.setInt(3, parentId);
                });

    }

}
