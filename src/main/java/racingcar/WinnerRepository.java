package racingcar;

import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class WinnerRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public WinnerRepository(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertWinner(final int gameId, final int playerId) {
        String insertWinnerSql = "INSERT INTO winners(game_id, player_id) VALUES(:game_id, :player_id)";

        SqlParameterSource namedParameters = new MapSqlParameterSource(Map.of(
                "game_id", gameId,
                "player_id", playerId
        ));

        jdbcTemplate.update(insertWinnerSql, namedParameters);
    }
}
