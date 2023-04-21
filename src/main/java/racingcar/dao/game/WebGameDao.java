package racingcar.dao.game;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.entity.GameEntity;

@Repository
public class WebGameDao implements GameDao {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<GameEntity> rowMapper = (resultSet, rowNum) -> new GameEntity(
            resultSet.getLong("id"),
            resultSet.getInt("trial_count"),
            resultSet.getTimestamp("created_at"));

    public WebGameDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(final int trialCount) {
        final String sql = "INSERT INTO GAME(trial_count) VALUES(?) ";
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setInt(1, trialCount);
            return preparedStatement;
        }, generatedKeyHolder);
        return Objects.requireNonNull(generatedKeyHolder.getKey()).longValue();
    }

    @Override
    public List<GameEntity> findAll() {
        final String sql = "SELECT * FROM GAME";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
