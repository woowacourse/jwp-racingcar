package racingcar.web.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.web.entity.GameResultEntity;

import java.sql.PreparedStatement;

@Component
public class GameResultDao {

    private final JdbcTemplate jdbcTemplate;

    public GameResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insert(GameResultEntity gameResultEntity) {
        String sql = "insert into game_result (try_count) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, gameResultEntity.getTryCount());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
}
