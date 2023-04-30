package racingcar.dao.game;

import java.util.HashMap;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.GameEntity;

@Repository
public class WebGameDao implements GameDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    private final RowMapper<GameEntity> rowMapper = (resultSet, rowNum) -> new GameEntity(
            resultSet.getLong("id"),
            resultSet.getInt("trial_count"),
            resultSet.getTimestamp("created_at"));

    public WebGameDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("game")
                .usingGeneratedKeyColumns("id")
                .usingColumns("trial_count");
    }

    @Override
    public Long save(final int trialCount) {
        final HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("trial_count", trialCount);
        return simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
    }

    @Override
    public List<GameEntity> findAll() {
        final String sql = "SELECT * FROM GAME";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
