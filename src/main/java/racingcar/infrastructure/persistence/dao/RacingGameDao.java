package racingcar.infrastructure.persistence.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.infrastructure.persistence.entity.RacingGameEntity;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Component
public class RacingGameDao {

    private final JdbcTemplate template;

    public RacingGameDao(final JdbcTemplate template) {
        this.template = template;
    }

    public Long save(final RacingGameEntity racingGameEntity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql = "INSERT INTO PLAY_RESULT (created_at, trial_count) VALUES (?, ?)";
        template.update(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, LocalDateTime.now().toString());
            preparedStatement.setInt(2, racingGameEntity.getTrialCount());
            return preparedStatement;
        }, keyHolder);

        return ((long) keyHolder.getKeys().get("id"));
    }

    public Optional<RacingGameEntity> findById(final Long id) {
        return Optional.ofNullable(
                template.queryForObject("SELECT * FROM PLAY_RESULT WHERE id = ?",
                        (rs, rowNum) -> new RacingGameEntity(
                                rs.getLong(1),
                                rs.getTimestamp(2).toLocalDateTime(),
                                rs.getInt(3)
                        ), id)
        );
    }
}
