package racingcar.dao;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.entity.Game;

@Component
public class GameJdbcDao implements GameDao {
    private final JdbcTemplate jdbcTemplate;

    public GameJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveAndGetId(final Game game) {
        final String sql = "insert into game (trial, created_at) values (?, ?)";

        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, game.getTrial());
            ps.setTimestamp(2, Timestamp.valueOf(game.getCreatedAt()));
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }
}
