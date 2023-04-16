package racingcar.dao;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.domain.entity.RacingGameEntity;

@Component
public class JdbcRacingGameDao implements RacingGameDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRacingGameDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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

    @Override
    public List<RacingGameEntity> findAll() {
        String sql = "SELECT * FROM RACING_GAME";
        return jdbcTemplate.query(sql, racingGameEntityRowMapper());
    }

    private RowMapper<RacingGameEntity> racingGameEntityRowMapper() {
        return (rs, rowNum) -> {
            final int id = rs.getInt("id");
            final int count = rs.getInt("count");
            final LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();

            return new RacingGameEntity(id, count, createdAt);
        };
    }
}
