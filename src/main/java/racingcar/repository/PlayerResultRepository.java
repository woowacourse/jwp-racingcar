package racingcar.repository;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dto.PlayerResultSaveDto;
import racingcar.entity.PlayerResult;

import javax.sql.DataSource;

@Repository
public class PlayerResultRepository {

    private final SimpleJdbcInsert insertPlayerResult;

    public PlayerResultRepository(final DataSource dataSource) {
        this.insertPlayerResult = new SimpleJdbcInsert(dataSource)
                .withTableName("player_result")
                .usingGeneratedKeyColumns("id");
    }

    public PlayerResult savePlayerResult(final PlayerResultSaveDto playerResult) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(playerResult);
        long id = insertPlayerResult.executeAndReturnKey(params).longValue();
        return new PlayerResult(id, playerResult.getName(), playerResult.getFinalPosition(), playerResult.getGameId());
    }
}
