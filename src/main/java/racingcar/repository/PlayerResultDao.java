package racingcar.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.PlayerResult;
import racingcar.repository.dto.GetPlayerResultQueryResponseDto;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PlayerResultDao {

    private final SimpleJdbcInsert insertPlayerResult;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlayerResultDao(final DataSource dataSource) {
        this.insertPlayerResult = new SimpleJdbcInsert(dataSource)
                .withTableName("player_result")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public PlayerResult save(final PlayerResult playerResult) {
        final SqlParameterSource params = new MapSqlParameterSource(makeParams(playerResult));
        final long id = insertPlayerResult.executeAndReturnKey(params).longValue();
        return new PlayerResult(id, playerResult);
    }

    private Map<String, Object> makeParams(final PlayerResult playerResult) {
        final Map<String, Object> params = new HashMap<>();
        params.put("name", playerResult.getName());
        params.put("final_position", playerResult.getFinalPosition());
        params.put("game_id", playerResult.getGameId());
        return new HashMap<>(params);
    }

    public List<GetPlayerResultQueryResponseDto> getAll() {
        final String sql = "select game.id, game.winners, player_result.name, player_result.final_position" +
                "from game, player_result" +
                "where game.id = player_result.game_id";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new GetPlayerResultQueryResponseDto(
                        resultSet.getLong("id"),
                        resultSet.getString("winners"),
                        resultSet.getString("name"),
                        resultSet.getInt("final_position")
                ));
    }
}
