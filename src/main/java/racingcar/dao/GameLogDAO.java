package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GameLogDAO {
    private JdbcTemplate jdbcTemplate;

    public GameLogDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    void insert(int gameNumber, String playerName, int resultPosition) {
        String sql = "insert into game_log (game_number, player_name, result_position) values (?, ?, ?)";
        jdbcTemplate.update(sql, gameNumber, playerName, resultPosition);
    }
}
