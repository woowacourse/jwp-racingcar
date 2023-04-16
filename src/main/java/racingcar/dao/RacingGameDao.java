package racingcar.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class RacingGameDao {

    private final SimpleJdbcInsert insertActor;

    public RacingGameDao(DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("racing_game")
                .usingColumns("winners", "trial_count")
                .usingGeneratedKeyColumns("id");
    }


    public Long save(final String winners, final Integer trialCount) {
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("winners", winners);
        parameters.put("trial_count", trialCount);
        return insertActor.executeAndReturnKey(parameters).longValue();

    }
}
