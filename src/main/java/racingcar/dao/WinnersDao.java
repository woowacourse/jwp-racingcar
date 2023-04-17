package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WinnersDao {

    private JdbcTemplate jdbcTemplate;

    public WinnersDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(int gameNumber, String winner) {
        String sql = "insert into winners(game_number, winner) values(?,?)";
        jdbcTemplate.update(sql, gameNumber, winner);
    }
}
