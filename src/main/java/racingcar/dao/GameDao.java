package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class GameDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertActor;

    public GameDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("game")
                .usingGeneratedKeyColumns("game_id");
    }

    public int save(GameEntity gameEntity) {
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(gameEntity);
        return insertActor.executeAndReturnKey(sqlParameterSource).intValue();
    }
}
