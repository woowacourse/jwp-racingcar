package racingcar.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PlayerDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(List<Player> players) {
        String sql = "insert into PLAYER (name, position, game_id) values (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Player player = players.get(i);
                ps.setString(1, player.getName());
                ps.setInt(2, player.getPosition());
                ps.setInt(3, player.getGameId());
            }

            @Override
            public int getBatchSize() {
                return players.size();
            }
        });
    }
}
