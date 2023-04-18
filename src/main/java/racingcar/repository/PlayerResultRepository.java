package racingcar.repository;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
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

    public PlayerResult save(final PlayerResult playerResult) {
        final SqlParameterSource params = new BeanPropertySqlParameterSource(playerResult);
        final long id = insertPlayerResult.executeAndReturnKey(params).longValue();
        return new PlayerResult(id, playerResult);
    }
}
