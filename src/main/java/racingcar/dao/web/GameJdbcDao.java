package racingcar.dao.web;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dao.GameDao;
import racingcar.dao.entity.GameEntity;

import java.util.List;

@Repository
public class GameJdbcDao implements GameDao {
    private final SimpleJdbcInsert insertActor;
    private final JdbcTemplate jdbcTemplate;
    public GameJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("game")
                .usingGeneratedKeyColumns("game_id");
    }

    public int save(GameEntity gameEntity) {
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(gameEntity);
        return insertActor.executeAndReturnKey(sqlParameterSource).intValue();
    }

    public List<Integer> findGameIds() {
        String sql = "SELECT game_id FROM GAME";
        return jdbcTemplate.query(sql,(resultSet, rowNum)-> {
            return resultSet.getInt("game_id");
        });
    }
}
