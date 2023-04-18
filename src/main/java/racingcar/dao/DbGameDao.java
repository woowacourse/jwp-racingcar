package racingcar.dao;

import java.util.HashMap;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.TryCount;

@Repository
public final class DbGameDao implements GameDao {
    private final SimpleJdbcInsert insertGame;
    private final JdbcTemplate jdbcTemplate;

    public DbGameDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.insertGame = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingColumns("trial_count")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
    }

    public Number insertGame(TryCount tryCount) {
        HashMap<String, Object> parameters = new HashMap<>();
        int trialCount = tryCount.getCount();
        parameters.put("trial_count", trialCount);
        return insertGame.executeAndReturnKey(parameters);
    }

    public List<Integer> selectGameIds() {
        final String sql = "SELECT (id) from GAME";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> resultSet.getInt("id"));
    }
}
