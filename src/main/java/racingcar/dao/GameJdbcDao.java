package racingcar.dao;

import java.util.Optional;
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
    public Optional<Integer> saveAndGetId(final GameEntity game) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(game);
        return Optional.of(jdbcInsert.executeAndReturnKey(params).intValue());
    }
}
