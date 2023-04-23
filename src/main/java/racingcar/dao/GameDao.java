package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.entity.Game;

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;

@Repository
public class GameDao {

    private final JdbcTemplate jdbcTemplate;

    public GameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insert(Game game) {
        String sql = "insert into GAME (winners, trial_count) values(?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, game.getWinners());
            ps.setInt(2, game.getTrialCount());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public List<Game> selectAll() {
        final var query = "SELECT * FROM GAME";

        return jdbcTemplate.query(query,
                (resultSet, rowNum) -> {
                    int id = resultSet.getInt("id");
                    String winners = resultSet.getString("winners");
                    int tryCount = resultSet.getInt("trial_count");
                    return Game.of(id, Arrays.asList(winners), tryCount);
                }

        );
    }
}
