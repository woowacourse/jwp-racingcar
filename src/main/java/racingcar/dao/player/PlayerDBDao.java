package racingcar.dao.player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
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
    public void insertPlayer(List<Player> players) {
        String sql = "INSERT INTO player(name, position, game_id) VALUES(?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(final PreparedStatement ps, final int i) throws SQLException {
                ps.setString(1, players.get(i).getName());
                ps.setInt(2, players.get(i).getPosition());
                ps.setLong(3, players.get(i).getGameId());
            }

            @Override
            public int getBatchSize() {
                return players.size();
            }
        });
    }

}
