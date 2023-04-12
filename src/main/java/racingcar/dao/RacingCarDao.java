package racingcar.dao;

import java.sql.PreparedStatement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import racingcar.domain.Car;

public class RacingCarDao {

    private final JdbcTemplate jdbcTemplate;

    public RacingCarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(final Long gameId, final Car car) {
        final String sql = "insert into racing_car (game_id, name, position) values (?, ?, ?)";

        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setLong(1, gameId);
            preparedStatement.setString(2, car.getName());
            preparedStatement.setInt(3, car.getPosition());
            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
}
