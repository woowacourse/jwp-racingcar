package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.Player;

import java.util.List;

@Repository
public class PlayerJdbcDao implements PlayerDao {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;

    public PlayerJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("player")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public void insertAll(final List<Player> players) {
        final BeanPropertySqlParameterSource[] parameters = players.stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(BeanPropertySqlParameterSource[]::new);

        simpleJdbcInsert.executeBatch(parameters);
    }

    @Override
    public List<Player> findAllPlayer(long playResultId) {
        String sql = "select * from PLAYER where play_result_id = ?";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> {
                    final Player player = new Player(
                            resultSet.getLong("id"),
                            playResultId,
                            resultSet.getString("name"),
                            resultSet.getInt("position"),
                            resultSet.getBoolean("winner")
                    );
                    return player;
                }, playResultId);
    }
}
