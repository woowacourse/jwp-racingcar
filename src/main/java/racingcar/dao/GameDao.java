package racingcar.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import racingcar.domain.TrialCount;

@Repository
public class GameDao {
    private final SimpleJdbcInsert simpleJdbcInsert;
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Integer> gameNumberMapper = (resultSet, rowNum) -> resultSet.getInt("game_number");

    @Autowired
    public GameDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingGeneratedKeyColumns("game_number", "created_at");
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveGame(TrialCount trialCount) {
        Map<String, Integer> parameters = new HashMap<>();
        parameters.put("trial_count", trialCount.getTrialCount());
        return (int) simpleJdbcInsert.executeAndReturnKeyHolder(parameters).getKeys().get("game_number");
    }

    public List<Integer> getGameNumbers() {
        String sql = "select game_number from game";
        return jdbcTemplate.query(sql, gameNumberMapper);
    }
}
