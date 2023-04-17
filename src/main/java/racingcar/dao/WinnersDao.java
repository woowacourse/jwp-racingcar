package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class WinnersDao {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;

    public WinnersDao(JdbcTemplate jdbcTemplate) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("winners");
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(final int gameId, final List<Integer> winnerIds) {
        for (Integer winnerId : winnerIds) {
            final SqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("game_id", gameId)
                    .addValue("car_id", winnerId);

            simpleJdbcInsert.execute(parameterSource);
        }
    }

    public List<Integer> findAllWinnerIdsByGameId(final int gameId) {
        final String sql = "SELECT car_id FROM winners WHERE game_id = ?";

        return jdbcTemplate.queryForList(sql, Integer.class, gameId);
    }
}
