package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.PlayerEntity;

import java.util.List;

@Repository
public class PlayerJdbcDao implements PlayerDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertActor;

    public PlayerJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("player")
                .usingGeneratedKeyColumns("id");
    }

    private final RowMapper<PlayerEntity> actorRowMapper = (resultSet, rowNum) -> new PlayerEntity(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getInt("position"),
            resultSet.getBoolean("is_winner"),
            resultSet.getInt("game_id")
    );

    public void saveAll(final List<PlayerEntity> players) {
        final BeanPropertySqlParameterSource[] parameterSources = players.stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(BeanPropertySqlParameterSource[]::new);
        insertActor.executeBatch(parameterSources);
    }

    @Override
    public List<PlayerEntity> findAll() {
        final String sql = "SELECT * FROM player";

        return jdbcTemplate.query(sql, actorRowMapper);
    }
}
