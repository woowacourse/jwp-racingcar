package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import racingcar.dao.entity.Player;

@Component
public class PlayerDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayerDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertPlayers(Player player) {
        String sql = "INSERT INTO player(name, position, game_id) VALUES(?, ?, ?)";

        jdbcTemplate.update(sql, player.getName(), player.getPosition(), player.getGameId());
    }

}
