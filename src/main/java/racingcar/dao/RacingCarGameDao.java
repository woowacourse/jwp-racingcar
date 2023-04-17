package racingcar.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import racingcar.dao.entity.Game;
import racingcar.dao.entity.Player;

@Component
public class RacingCarGameDao {

    private final JdbcTemplate jdbcTemplate;

    public RacingCarGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insertGameWithKeyHolder(Game game) {
        String sql = "INSERT INTO game(play_count, winners) VALUES(?, ?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, game.getPlayCount());
            ps.setString(2, game.getWinners());
            return ps;
        }, keyHolder);

        return Long.valueOf(String.valueOf(keyHolder.getKeys().get("game_id")));

    }
}
