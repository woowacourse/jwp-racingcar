package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.entity.GameEntity;

public class SelectGameDao {

    private final JdbcTemplate jdbcTemplate;

    public SelectGameDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<GameEntity> findAll() {
        final String sql = "SELECT game_id,trial_count FROM GAME";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new GameEntity(rs.getInt("game_id"), rs.getInt("trial_count")));
    }
}
