package racingcar.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class RacingGameDao {

    final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
    private final JdbcTemplate jdbcTemplate;

    public RacingGameDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(final int trialCount) {
        final String sql = "INSERT INTO racing_game(trial_count) VALUES (?)";
        this.jdbcTemplate.update(con -> {
            final PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setInt(1, trialCount);
            return preparedStatement;
        }, keyHolder);
    }

    public int getGameId() {
        return keyHolder.getKey().intValue();
    }
}
