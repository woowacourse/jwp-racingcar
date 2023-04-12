package racingcar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WinnerInsertDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertWinner(int gameId, int playerId) {
        String insertWinnerSql = "INSERT INTO winners(game_id, player_id) VALUES(?, ?)";
        jdbcTemplate.update(insertWinnerSql, gameId, playerId);
    }
}
