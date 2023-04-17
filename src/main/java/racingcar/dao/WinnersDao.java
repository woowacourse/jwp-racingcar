package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class WinnersDao {

    private JdbcTemplate jdbcTemplate;

    public WinnersDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<String> winnerRowMapper = (resultSet, rowNum) -> resultSet.getString("winner");

    public void insert(int gameNumber, String winner) {
        String sql = "insert into winners(game_number, winner) values(?,?)";
        jdbcTemplate.update(sql, gameNumber, winner);
    }

    public List<String> find(int gameNumber) {
        String sql = "select w.winner from winners w where w.game_number = ?";
        return jdbcTemplate.query(sql, winnerRowMapper, gameNumber);
    }
}
