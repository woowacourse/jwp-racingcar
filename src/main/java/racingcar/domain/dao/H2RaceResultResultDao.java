package racingcar.domain.dao;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.domain.dao.entity.RaceEntity;

@Component
public class H2RaceResultResultDao implements RaceResultDao {

    private final JdbcTemplate jdbcTemplate;

    public H2RaceResultResultDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long save(final int trialCount, final String winners) {
        final String query = "INSERT INTO race_result (trial_count, winners, created_at) VALUES (?, ?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            final PreparedStatement preparedStatement = con.prepareStatement(query,
                new String[]{"race_result_id"});
            preparedStatement.setString(1, String.valueOf(trialCount));
            preparedStatement.setString(2, winners);
            preparedStatement.setString(3, LocalDateTime.now().toString());
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public List<RaceEntity> findAll() {
        final String query = "SELECT * FROM race_result";
        return jdbcTemplate.query(query, (result, count) -> new RaceEntity(
            result.getLong("race_result_id"),
            result.getInt("trial_count"),
            result.getString("winners")
        ));
    }
}
