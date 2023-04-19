package racingcar.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.PlayerResult;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PlayerResultDao {

    private final SimpleJdbcInsert insertPlayerResult;

    @Autowired
    public PlayerResultDao(final DataSource dataSource) {
        this.insertPlayerResult = new SimpleJdbcInsert(dataSource)
                .withTableName("player_result")
                .usingGeneratedKeyColumns("id");
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
        params.put("game_id", playerResult.getGame().getId());
        return new HashMap<>(params);
    }
}
