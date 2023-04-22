package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.PlayerResult;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PlayerResultDao {

    private final SimpleJdbcInsert insertPlayerResult;
    private final JdbcTemplate jdbcTemplate;

    public PlayerResultDao(final DataSource dataSource) {
        this.insertPlayerResult = new SimpleJdbcInsert(dataSource)
                .withTableName("player_result")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public PlayerResult save(final PlayerResult playerResult) {
        final SqlParameterSource params = new MapSqlParameterSource(makeParams(playerResult));
        final long id = insertPlayerResult.executeAndReturnKey(params).longValue();
        return new PlayerResult(id, playerResult.getName(), playerResult.getFinalPosition(), playerResult.getGameId());
    }

    private Map<String, Object> makeParams(final PlayerResult playerResult) {
        final Map<String, Object> params = new HashMap<>();
        params.put("name", playerResult.getName());
        params.put("final_position", playerResult.getFinalPosition());
        params.put("game_id", playerResult.getGameId());
        return params;
    }

    public List<PlayerResult> findByGameId(final long gameId) {
        final String sql = "select *" +
                " from player_result" +
                " where game_id = ?";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new PlayerResult(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("final_position"),
                        resultSet.getLong("game_id")
                ), gameId);
    }
}
