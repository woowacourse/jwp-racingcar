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

    public void insert(final int trialCount, final String winners) {
        final String sql = "INSERT INTO racing_game(trial_count,winners) VALUES (?,?)";
        this.jdbcTemplate.update(con -> {
            final PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setInt(1, trialCount);
            preparedStatement.setString(2, winners);
            return preparedStatement;
        }, keyHolder);
    }

    public int getGameId() {
        return keyHolder.getKey().intValue();
    }
}
