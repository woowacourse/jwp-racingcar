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
    public void insert(final String name, final int position, final long playResultId) {
        String sql = "insert into PLAYER (name, position, play_result_id) values (?, ?, ?)";
        jdbcTemplate.update(sql, name, position, playResultId);
    }

    @Override
    public List<Player> findAllPlayer(long playResultId) {
        String sql = "select * from PLAYER where play_result_id = ?";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> {
                    final Player player = new Player(
                            resultSet.getLong("id"),
                            resultSet.getLong("play_result_id"),
                            resultSet.getString("name"),
                            resultSet.getInt("position")
                    );
                    return player;
                }, playResultId);
    }
}
