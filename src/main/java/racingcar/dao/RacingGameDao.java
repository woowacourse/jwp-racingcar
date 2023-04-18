package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.entity.RacingGameEntity;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Component
public class RacingGameDao {

    private final JdbcTemplate jdbcTemplate;

    public RacingGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(final RacingGameEntity racingGameEntity) {
        final String sql = "INSERT INTO RACING_GAME (trial_count) VALUES (?)";

        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setInt(1, racingGameEntity.getTrialCount());
            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public List<RacingGameEntity> findAll() {
        final String sql = "SELECT * FROM RACING_GAME";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> RacingGameEntity.builder()
                        .id(rs.getLong(1))
                        .trialCount(rs.getInt(2))
                        .date(new Timestamp(rs.getDate(3).getTime()).toLocalDateTime())
                        .build()
        );
    }
}
