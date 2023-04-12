package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayerDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(final String name, final int position, final long playResultId) {
        String sql = "insert into PLAYER (name, position, play_result_id) values (?, ?, ?)";
        jdbcTemplate.update(sql, name, position, playResultId);
    }
}
