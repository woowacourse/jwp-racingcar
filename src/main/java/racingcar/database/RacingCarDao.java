package racingcar.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.model.Car;

import java.sql.PreparedStatement;
import java.util.List;

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

    public List<String> selectWinners(final int gameId) {
        final String sql = "SELECT name FROM racing_car WHERE racing_game_id = ? and is_winner = true";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> resultSet.getString("name"),
                gameId
        );
    }

    public List<Car> selectCarsBy(final int gameId) {
        final String sql = "SELECT name, position FROM racing_car WHERE racing_game_id = ?";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new Car(resultSet.getString("name"), resultSet.getInt("position")),
                gameId
        );
    }
}
