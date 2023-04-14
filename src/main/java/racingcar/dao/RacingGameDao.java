package racingcar.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RacingGameDao {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public RacingGameDao(final DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("racing_game")
                .usingColumns("winners", "trial_count")
                .usingGeneratedKeyColumns("id");
    }

    public Long save(final String winners, final Integer trialCount) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("winners", winners);
        parameters.put("trial_count", trialCount);
        return simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
    }
}
