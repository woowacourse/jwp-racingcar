package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GameLogDao {
    private final JdbcTemplate jdbcTemplate;

    public GameLogDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(final int gameNumber,final String playerName,final int resultPosition) {
        String sql = "insert into game_log (game_number, player_name, result_position) values (?, ?, ?)";
        jdbcTemplate.update(sql, gameNumber, playerName, resultPosition);
    }
}
