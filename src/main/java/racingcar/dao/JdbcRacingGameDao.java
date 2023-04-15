package racingcar.dao;

import java.sql.PreparedStatement;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class JdbcRacingGameDao implements RacingGameDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRacingGameDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int save(int count) {
        String sql = "INSERT INTO RACING_GAME(count) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, count);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }
}
