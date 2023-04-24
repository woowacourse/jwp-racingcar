package racingcar.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class JdbcRacingGameDao implements RacingGameDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRacingGameDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(final int trialCount) {
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        final String sql = "INSERT INTO racing_game(trial_count) VALUES (?)";
        this.jdbcTemplate.update(con -> {
            final PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setInt(1, trialCount);
            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public List<Integer> selectGameIds() {
        final String sql = "SELECT id FROM racing_game";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> resultSet.getInt("id")
        );
    }
}
