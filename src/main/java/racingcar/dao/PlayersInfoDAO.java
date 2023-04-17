package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PlayersInfoDAO {

    private final JdbcTemplate jdbcTemplate;

    public PlayersInfoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(int parentId, List<Car> cars) {
        String sql = "insert into players_info (name, position, parent_id) values (?, ?, ?)";
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
