package racingcar.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.entity.GameResultEntity;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class GameResultDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameResultDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Number save(final String winners, final int trialCount) {
        final String sql = "INSERT INTO GAME_RESULT (winners, trial_count) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, winners);
            preparedStatement.setInt(2, trialCount);
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey();
    }

    public List<GameResultEntity> selectAll() {
        final String sql = "SELECT * FROM GAME_RESULT";

        return jdbcTemplate.query(sql, (resultSet, count) -> new GameResultEntity(
                resultSet.getInt("id"),
                resultSet.getInt("trial_count"),
                resultSet.getString("winners"),
                resultSet.getTimestamp("created_at").toLocalDateTime()
        ));
    }
}
