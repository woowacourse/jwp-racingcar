package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.domain.Car;

import java.sql.PreparedStatement;
import java.util.List;

@Component
public final class CarDao {

    private final JdbcTemplate jdbcTemplate;

    public CarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(final Car car) {
        String sql = "INSERT INTO car (player_name, final_position, is_winner, game_result_id) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, car.getNameValue());
            ps.setInt(2, car.getPositionValue());
            ps.setBoolean(3, car.isWinner());
            ps.setLong(4, car.getId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public void saveAll(final List<Car> result) {
        for (Car car : result) {
            save(car);
        }
    }
}
