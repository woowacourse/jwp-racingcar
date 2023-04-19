package racingcar.repository.h2;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import racingcar.entity.GameEntity;
import racingcar.repository.GameRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class H2GameRepository implements GameRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public H2GameRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("game")
                .usingColumns("trial_count")
                .usingGeneratedKeyColumns("id");
    }

    private RowMapper<GameEntity> gameRowMapper() {
        return (resultSet, rowNum) ->
                GameEntity.of(
                        resultSet.getLong("id"),
                        resultSet.getInt("trial_count"),
                        resultSet.getTimestamp("created_at")
                );
    }

    public GameEntity save(GameEntity gameEntity) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("trial_count", gameEntity.getTrialCount());
        long id = simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
        return findById(id);
    }

    public GameEntity findById(long id) {
        String sql = "SELECT * FROM game WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, gameRowMapper(), id);
    }

    public List<GameEntity> findAll() {
        String sql = "SELECT * FROM game";
        return jdbcTemplate.query(sql, gameRowMapper());
    }
}
