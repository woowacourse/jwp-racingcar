package racingcar.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.model.Car;

import java.sql.PreparedStatement;

@Repository
public class RacingCarDao {
    private final JdbcTemplate jdbcTemplate;

    public RacingCarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(final Car car, final int gameId, final boolean isWinner) {
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        final String sql = "INSERT INTO racing_car(name,position,racing_game_id,is_winner) VALUES (?,?,?,?)";
        this.jdbcTemplate.update(con -> {
            final PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, car.getName());
            preparedStatement.setInt(2, car.getPosition());
            preparedStatement.setInt(3, gameId);
            preparedStatement.setBoolean(4, isWinner);
            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }
}
