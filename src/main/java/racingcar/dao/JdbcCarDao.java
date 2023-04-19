package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCarDao implements CarDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(final String name, final int position, final Long gameId, final boolean isWin) {
        String sql = "INSERT INTO car(name, position, game_id, is_win) VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql, name, position, gameId, isWin);
    }


    @Override
    public int countRows() {
        final String sql = "select count(*) from car";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM car");
    }
}
