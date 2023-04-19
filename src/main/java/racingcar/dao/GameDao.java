package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GameDao {
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;
    public GameDao(final DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingGeneratedKeyColumns("game_number", "created_at");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long saveGame(final int trialCount) {
        Map<String, Integer> parameters = new HashMap<>();
        parameters.put("trial_count", trialCount);
        return (long) simpleJdbcInsert.executeAndReturnKeyHolder(parameters).getKeys().get("game_number");
    }

    public List<Long> load(){
        final String sql = "select game_number from game";
        return jdbcTemplate.query(
                sql,
                (resultSet,rowNum)-> resultSet.getLong("game_number")
        );
    }
}
