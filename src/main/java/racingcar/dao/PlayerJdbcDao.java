package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.entity.Player;

import java.util.List;

@Repository
public class PlayerJdbcDao implements PlayerDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayerJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(final long playResultId, final String name, final int position, boolean winner) {
        String sql = "insert into PLAYER (play_result_id, name, position, winner) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, playResultId, name, position, winner);
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
