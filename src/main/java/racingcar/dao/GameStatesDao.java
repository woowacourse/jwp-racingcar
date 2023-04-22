package racingcar.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import racingcar.dao.dto.GameStateDto;

@Component
public class GameStatesDao {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;

    public GameStatesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("gamestates")
                .usingColumns("initial_trial_count", "remaining_trial_count")
                .usingGeneratedKeyColumns("id");
    }

    public int insert(final int initialTrialCount, final int remainingTrialCount) {
        final SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("initial_trial_count", initialTrialCount)
                .addValue("remaining_trial_count", remainingTrialCount);

        return simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue();
    }

    public List<GameStateDto> selectAll() {
        String sql = "SELECT id, initial_trial_count, remaining_trial_count FROM gamestates";

        return jdbcTemplate.query(sql,
                (resultSet, rowCount) -> new GameStateDto(
                        resultSet.getInt("id"),
                        resultSet.getInt("initial_trial_count"),
                        resultSet.getInt("remaining_trial_count")
                )
        );
    }
}
