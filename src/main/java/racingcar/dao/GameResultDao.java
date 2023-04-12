package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.domain.TryCount;

import java.sql.PreparedStatement;

@Component
public class GameResultDao {

    private final JdbcTemplate jdbcTemplate;

    public GameResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insert(TryCount tryCount) {
        String sql = "insert into game_result (try_count) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, tryCount.getCount());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
}
