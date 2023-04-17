package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import racingcar.dao.entity.SelectGameEntity;

public class SelectGameDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<SelectGameEntity> actorRowMapper = (resultSet, rowNum) -> new SelectGameEntity(
            resultSet.getInt("game_id"),
            resultSet.getTimestamp("created_at").toLocalDateTime(),
            resultSet.getInt("trial_count")
    );

    public SelectGameDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SelectGameEntity> findAll() {
        final String sql = "SELECT game_id, created_at, trial_count FROM GAME";

        return jdbcTemplate.query(sql, actorRowMapper);
    }
}
