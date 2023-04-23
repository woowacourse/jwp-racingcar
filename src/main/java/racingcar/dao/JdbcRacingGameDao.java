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
    private final RowMapper<RacingGameEntity> rowMapper = (rs, rowNum) -> {
        int id = rs.getInt("id");
        int count = rs.getInt("count");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();

        return new RacingGameEntity(id, count, createdAt);
    };


    public JdbcRacingGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(int count) {
        String sql = "INSERT INTO RACING_GAME(count) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, count);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public List<RacingGameEntity> findAll() {
        String sql = "SELECT * FROM RACING_GAME";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
