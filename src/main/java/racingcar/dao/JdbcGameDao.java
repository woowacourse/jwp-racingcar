package racingcar.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.entity.GameEntity;

@Repository
public class JdbcGameDao implements GameDao {
    private final SimpleJdbcInsert insertActor;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<GameEntity> actorRowMapper = (resultSet, rowNum) -> new GameEntity(
        resultSet.getInt("id"),
        resultSet.getString("winners"),
        resultSet.getInt("trial")
    );

    public JdbcGameDao(final DataSource dataSource, final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
            .withTableName("GAME")
            .usingGeneratedKeyColumns("id");
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public GameEntity insertGame(final GameEntity gameEntity) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(gameEntity);

        Number newId = insertActor.executeAndReturnKey(parameterSource);
        gameEntity.setId(newId.intValue());
        return gameEntity;
    }

    @Override
    public List<GameEntity> selectAllGames() {
        String sql = "select id, trial, winners from GAME order by id desc";
        return namedParameterJdbcTemplate.query(sql, actorRowMapper);
    }
}
