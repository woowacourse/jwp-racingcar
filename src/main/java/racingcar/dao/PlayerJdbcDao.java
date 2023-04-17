package racingcar.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import racingcar.entity.Player;

import javax.sql.DataSource;
import java.util.List;

@Component
public class PlayerJdbcDao implements PlayerDao {
    private final SimpleJdbcInsert insertActor;

    public PlayerJdbcDao(final DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("player")
                .usingGeneratedKeyColumns("id");
    }

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
}
