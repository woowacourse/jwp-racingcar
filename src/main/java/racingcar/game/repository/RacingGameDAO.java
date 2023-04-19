package racingcar.game.repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.game.interfaces.GameResult;

@Repository
public class RacingGameDAO {
    
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    
    public RacingGameDAO(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate)
                .withTableName("racing_game")
                .usingGeneratedKeyColumns("id");
    }
    
    public int insert(final int trialCount, final GameResult gameResult) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("trial_count", trialCount);
        parameters.put("winners", gameResult.getWinners());
        parameters.put("created_at", new Timestamp(gameResult.getCreatedAt()));
        return this.simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
    }
}
