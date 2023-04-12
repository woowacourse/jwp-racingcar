package racingcar;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;

@Repository
public class RacingGameRepository {

    final private JdbcTemplate jdbcTemplate;

    public RacingGameRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(TryCount tryCount, Cars finalResult, Cars winnersResult) {
        String sql = "INSERT INTO game_result VALUES(trial_count)";
    }
}
