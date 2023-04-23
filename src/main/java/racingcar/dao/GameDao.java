package racingcar.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class GameDao {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Integer> gameNumberMapper
            = (resultSet, rowNum) -> resultSet.getInt("game_id");

    public GameDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingGeneratedKeyColumns("game_id", "created_at");
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveGame(int trialCount) {
        Map<String, Integer> parameters = new HashMap<>();
        parameters.put("trial_count", trialCount);
        return (int) simpleJdbcInsert.executeAndReturnKeyHolder(parameters).getKeys().get("game_id");
    }

    public List<Integer> getGameNumbers() {
        String sql = "select game_id from game";
        return jdbcTemplate.query(sql, gameNumberMapper);
    }
}
