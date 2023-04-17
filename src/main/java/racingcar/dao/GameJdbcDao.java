package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import racingcar.entity.GameEntity;

@Component
public class GameJdbcDao implements GameDao {
    private final SimpleJdbcInsert jdbcInsert;

    public GameJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Game")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public int saveAndGetId(final GameEntity game) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(game);
        return jdbcInsert.executeAndReturnKey(params).intValue();
    }
}
