package racingcar.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import racingcar.Entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class PlayerJdbcDao implements PlayerDao {
    private final JdbcTemplate jdbcTemplate;

    public PlayerJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveAll(final List<Player> players) {
        final String sql = "INSERT INTO player(name, position, game_id) VALUES (?, ?, ?)";
        final BatchPreparedStatementSetter batchPreparedStatementSetter = new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                final Player player = players.get(i);
                ps.setString(1, player.getName());
                ps.setInt(2, player.getPosition());
                ps.setInt(3, player.getGame_id());
            }

            @Override
            public int getBatchSize() {
                return players.size();
            }
        };
        jdbcTemplate.batchUpdate(sql, batchPreparedStatementSetter);
    }
}
