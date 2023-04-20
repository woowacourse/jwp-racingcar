package racingcar.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.entity.Player;

@Repository
public class PlayerJdbcDao implements PlayerDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Player> playerRowMapper = (resultSet, rowNum) ->
            new Player(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("position"),
                    resultSet.getInt("game_id"),
                    resultSet.getBoolean("is_winner")
            );

    public PlayerJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(List<Player> players) {

        String sql = "insert into PLAYER (name, position, game_id, is_winner) values (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Player player = players.get(i);
                ps.setString(1, player.getName());
                ps.setInt(2, player.getPosition());
                ps.setInt(3, player.getGameId());
                ps.setBoolean(4, player.isWinner());
            }

            @Override
            public int getBatchSize() {
                return players.size();
            }
        });
    }

    public List<Player> findAll() {
        String sql = "select * from PLAYER";

        return jdbcTemplate.query(sql, playerRowMapper);
    }
}
