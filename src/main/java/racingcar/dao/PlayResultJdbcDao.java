package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.PlayResult;

import java.util.HashMap;
import java.util.List;

@Repository
public class PlayResultJdbcDao implements PlayResultDao {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;

    public PlayResultJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("play_result")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public long insert() {
        return simpleJdbcInsert.executeAndReturnKey(new HashMap<>()).longValue();
    }

    @Override
    public List<PlayResult> findAllPlayResult() {
        String sql = "select * from PLAY_RESULT";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> {
                    final PlayResult playResult = new PlayResult(
                            resultSet.getLong("id")
                    );
                    return playResult;
                });
    }
}
