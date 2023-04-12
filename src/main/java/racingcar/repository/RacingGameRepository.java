package racingcar.repository;

import java.sql.PreparedStatement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class RacingGameRepository {

    private final JdbcTemplate jdbcTemplate;

    public RacingGameRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(final String winners, final int count) {
        final String sql = "insert into racing_game(winners, trial) values(?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            final PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, winners);
            ps.setInt(2, count);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }
}
