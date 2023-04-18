package racingcar.dao.player;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import racingcar.dao.entity.Player;

@Component
public class PlayerDBDao implements PlayerDao{

    private final JdbcTemplate jdbcTemplate;

    public PlayerDBDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertPlayer(Player player) {
        String sql = "INSERT INTO player(name, position, game_id) VALUES(?, ?, ?)";

        jdbcTemplate.update(sql, player.getName(), player.getPosition(), player.getGameId());
    }

}
