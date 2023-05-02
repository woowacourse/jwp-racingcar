package racingcar.dao;

import java.util.HashMap;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.RacingCars;
import racingcar.domain.TryCount;

@Component
public class RacingCarGameJdbcDao implements RacingCarGameDao {

    private final SimpleJdbcInsert insertActor;

    public RacingCarGameJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("RACING_CAR_GAME_RESULT")
                .usingGeneratedKeyColumns("id", "created_at");
    }

    @Transactional
    public int insertGame(final RacingCars racingCars, final TryCount tryCount) {
        final HashMap<String, String> parameter = new HashMap<>();

        parameter.put("winners", concat(racingCars.getWinnerNames()));
        parameter.put("trial_count", String.valueOf(tryCount.getCount()));

        return (int) insertActor.executeAndReturnKeyHolder(parameter).getKeys().get("id");
    }

    private String concat(final List<String> names) {
        return String.join(",", names);
    }
}
