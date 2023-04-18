package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.Player;

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

    private final RowMapper<Player> actorRowMapper = (resultSet, rowNum) -> new Player(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getInt("position"),
            resultSet.getBoolean("isWinner"),
            resultSet.getInt("game_id")
    );

    public void saveAll(final List<Player> players) {
        final MapSqlParameterSource[] batch = players.stream().map(player ->
                new MapSqlParameterSource()
                        .addValue("game_id", player.getGame_id())
                        .addValue("name", player.getName())
                        .addValue("position", player.getPosition())
                        .addValue("is_winner", player.isWinner())
        ).toArray(MapSqlParameterSource[]::new);

        insertActor.executeBatch(batch);
    }

    @Override
    public List<Player> findByGameId(final int gameId) {
        final String sql = "SELECT * FROM player where game_id =?";

        return jdbcTemplate.query(sql, actorRowMapper, gameId);
    }
}
