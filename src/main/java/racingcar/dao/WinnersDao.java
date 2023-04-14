package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WinnersDao {

    private final JdbcTemplate jdbcTemplate;

    public WinnersDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(final int gameNumber,final String winner) {
        String sql = "insert into winners(game_number, winner) values(?,?)";
        jdbcTemplate.update(sql, gameNumber, winner);
    }
}
