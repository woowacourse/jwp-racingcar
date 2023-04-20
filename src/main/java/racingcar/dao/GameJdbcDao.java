package racingcar.dao;

import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.entity.Game;

@Repository
public class GameJdbcDao implements GameDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Game> gameRowMapper = (resultSet, rowNum) ->
            new Game(
                    resultSet.getInt("id"),
                    resultSet.getString("winners"),
                    resultSet.getInt("trial_count"),
                    resultSet.getTimestamp("created_at").toLocalDateTime()
            );

    public GameJdbcDao(JdbcTemplate jdbcTemplate) {
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

    public List<Game> findAll() {
        String sql = "select * from GAME ORDER BY created_at ASC";

        return jdbcTemplate.query(sql, gameRowMapper);
    }
}
