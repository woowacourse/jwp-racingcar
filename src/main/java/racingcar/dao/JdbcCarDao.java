package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCarDao implements CarDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(final String name, final int position, final Long gameId, final boolean isWin) {
        String sql = "INSERT INTO car(name, position, game_id, is_win) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, name, position, gameId, isWin);
    }
}
