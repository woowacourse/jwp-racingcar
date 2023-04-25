package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.GameEntity;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Repository
public class GameJdbcTemplateDao implements GameDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameJdbcTemplateDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<GameEntity> findAll() {
        final String sql = "SELECT id, trial_count FROM GAME ";
        final RowMapper<GameEntity> gameEntityRowMapper = (resultSet, rowNum) ->
                new GameEntity(
                        resultSet.getLong("id"),
                        resultSet.getInt("trial_count"));
        return jdbcTemplate.query(sql, gameEntityRowMapper);
    }

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
}
