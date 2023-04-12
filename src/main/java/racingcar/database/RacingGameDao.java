package racingcar.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;

public class RacingGameDao {

    private final JdbcTemplate jdbcTemplate;

    public RacingGameDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(final int trialCount, final String winners) {
        final String sql = "INSERT INTO racing_game(trial_count,winners) VALUES (?,?)";
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(con -> {
            final PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setInt(1, trialCount);
            preparedStatement.setString(2, winners);
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }
}
