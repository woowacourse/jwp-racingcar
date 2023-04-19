package racingcar.repository.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.repository.entity.GameEntity;

@Repository
public class JdbcGameDao implements GameDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<GameEntity> actorRowMapper = (resultSet, rowNum) -> new GameEntity(
            resultSet.getLong("game_id"),
            resultSet.getInt("trial_count"),
            resultSet.getString("created_at")
    );

    @Autowired
    public JdbcGameDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long save(final GameEntity gameEntity) {
        final String sql = "INSERT INTO game (trial_count) VALUES (?)";
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, gameEntity.getTrialCount());
            return preparedStatement;
        }, keyHolder);

        return (long) Objects.requireNonNull(keyHolder.getKeys()).get("GAME_ID");
    }

    @Override
    public List<GameEntity> findAll() {
        final String sql = "SELECT game_id, trial_count, created_at FROM game";
        return jdbcTemplate.query(sql, actorRowMapper);
    }
}
